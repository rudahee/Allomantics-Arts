package net.rudahee.metallics_arts.modules.logic.server.server_events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.rudahee.metallics_arts.data.enums.implementations.EttmetalState;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.LerasiumEttmetalMetalmind;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.abstracts.MetalmindAbstract;
import net.rudahee.metallics_arts.modules.error_handling.exceptions.PlayerException;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import top.theillusivec4.curios.api.CuriosApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles events related to living entity drops.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnLivingEntityDropEvent {

    /**
     * This method is triggered when a LivingDropsEvent occurs. If the player is tapping
     * Zinc, it duplicates the drops, excluding Nether Stars. If the player is storing
     * Zinc, it cancels the event, preventing any drops from being spawned.
     *
     * @param event The LivingDropsEvent that triggered this method.
     */
    public static void livingEntityDrop(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer) {
            if (!(event.getEntity() instanceof ServerPlayer)) {

                try {

                    IInvestedPlayerData capabilitySource = CapabilityUtils.getCapability(event.getSource().getEntity());

                    if (capabilitySource.isTapping(MetalTagEnum.ZINC)) {
                        Collection<ItemEntity> drops = event.getDrops();
                        List<ItemEntity> filteredDrops = drops.stream().filter(e -> e.getItem().getItem() != Items.NETHER_STAR).collect(Collectors.toList());
                        event.getDrops().addAll(filteredDrops);

                    } else if (capabilitySource.isStoring(MetalTagEnum.ZINC)) {
                        event.setCanceled(true);
                    }

                } catch (PlayerException ex) {
                    ex.printCompleteLog();
                }

            }

        } else if (event.getEntity() instanceof ServerPlayer) {

            try {
                IInvestedPlayerData capabilityTarget = CapabilityUtils.getCapability(event.getEntity());

                if (capabilityTarget.getEttmetalState().equals(EttmetalState.KEEP_ITEMS) || capabilityTarget.getEttmetalState() == EttmetalState.DELETE_ITEMS) {
                    if (capabilityTarget.getEttmetalState().equals(EttmetalState.KEEP_ITEMS)) {

                        Inventory inventory = new Inventory((Player) event.getEntity());
                        List<ItemStack> itemStacks = event.getDrops().stream().map(ItemEntity::getItem).toList();

                        for (ItemStack itemStack : itemStacks) {
                            inventory.add(itemStack);
                        }
                        ((ServerPlayer) event.getEntity()).getInventory().replaceWith(inventory);
                    }

                    event.setCanceled(true);
                }

            } catch (PlayerException ex) {
                ex.printCompleteLog();
            }
        }
    }

}
