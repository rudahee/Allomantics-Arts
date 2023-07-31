package net.rudahee.metallics_arts.modules.custom_items.weapons.guns;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.GunType;
import net.rudahee.metallics_arts.data.enums.implementations.GunsAccess;
import net.rudahee.metallics_arts.setup.registries.ModKeyRegister;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The BasicGun class represents a basic gun item that extends the Item class.
 * It allows the player to use, reload, and interact with the gun in the game.
 *
 * @author SteelCode Team
 * @since 1.6.5
 *
 * @see Item
 * @see GunUtils
 * @see ShotGun
 * @see Rifle
 */


public class BasicGun extends Item  {
    private GunType gunType;


    /**
     * Constructs a new BasicGun with the given properties and gun type.
     *
     * @param properties The properties of the basic gun.
     * @param gunType    The Weapon type
     * @see GunType
     */
    public BasicGun(Properties properties, GunType gunType) {
        super(properties);
        this.gunType = gunType;
    }

    /**
     * Handles the use interaction of the basic gun. Updates the gun's custom model data
     * and starts using the item.
     *
     * @param level for the player.
     * @param player The player who uses the basic gun.
     * @param hand The hand used to interact with the basic gun.
     * @return The result of the interaction with the basic gun.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        CompoundTag tag = itemStack.getTag();
        tag.putFloat("CustomModelData", 1);
        itemStack.setTag(tag);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }

    /**
     * Called every tick for the gun item while it is in the player's inventory.
     * It handles actions and updates related to the gun item's state.
     *
     * @param stack The ItemStack representing the gun item.
     * @param level The current game level.
     * @param entity The entity holding the gun item.
     * @param selectedSlot The selected inventory slot of the player.
     * @param hasItemSelected A flag indicating whether the player has an item selected.
     */
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int selectedSlot, boolean hasItemSelected) {
        if (!stack.hasTag()) {
            stack.setTag(GunUtils.generateGunTags(this.gunType));
        }

        if (entity instanceof Player player) {
            if (player.getItemInHand(InteractionHand.MAIN_HAND) == stack) {
                if (ModKeyRegister.RELOAD.isDown()) {
                    if (stack.getTag().getInt(GunsAccess.BULLETS.getKey()) < stack.getTag().getInt(GunsAccess.BULLETS_MAX.getKey())) {
                        stack.getTag().putString(GunsAccess.STATE.getKey(), GunsAccess.RELOAD.getKey());
                        stack.getTag().putFloat("CustomModelData", 2);
                    }
                }
            } else if (stack.getTag().getString(GunsAccess.STATE.getKey()).equals(GunsAccess.RELOAD.getKey())) {
                stack.getTag().putString(GunsAccess.STATE.getKey(), GunsAccess.READY.getKey());
                stack.getTag().putFloat("CustomModelData", 0);
            }
        }
        super.inventoryTick(stack, level, entity, selectedSlot, hasItemSelected);
    }

    /**
     * Adds additional information to the tooltip when hovering over the gun item.
     *
     * @param stack The ItemStack representing the gun item.
     * @param level The current game level.
     * @param toolTips The list of tooltip components to add information to.
     * @param flagIn The tooltip flag for additional control.
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (stack.getTag().contains(GunsAccess.BULLET_TYPE.getKey())) {
                toolTips.add(Component.translatable(stack.getTag().getString(GunsAccess.BULLET_TYPE.getKey())));
            } else {
                stack.setTag(GunUtils.generateGunTags(this.gunType));
            }
        }
        super.appendHoverText(stack, level, toolTips, flagIn);
    }

    /**
     * Checks if the durability bar should be visible for the gun item.
     *
     * @param itemStack The ItemStack representing the gun item.
     * @return True if the durability bar should be visible, otherwise false.
     */
    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        if (itemStack.hasTag()) {
            if (itemStack.getTag().contains(GunsAccess.BULLETS.getKey())) {
                return itemStack.getTag().getInt(GunsAccess.BULLETS.getKey()) > 0;
            }
            return false;
        }
        return false;
    }

    /**
     * Gets the width of the durability bar for the gun item.
     *
     * @param itemStack The ItemStack representing the gun item.
     * @return The width of the durability bar.
     */
    @Override
    public int getBarWidth(ItemStack itemStack) {
        return super.getBarWidth(itemStack);
    }

    /**
     * Gets the type of the gun.
     *
     * @return The GunType of the gun.
     */
    public GunType getGunType() {
        return gunType;
    }

    /**
     * Called when an entity swings the gun item.
     *
     * @param stack The ItemStack representing the gun item.
     * @param entity The entity swinging the gun item.
     * @return Always returns true to allow entity swinging with the gun.
     */
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }

    /**
     * Called when the player releases the use button while using the gun item.
     *
     * @param itemStack The ItemStack representing the gun item.
     * @param level The current game level.
     * @param livingEntity The living entity using the gun item.
     * @param remainingTicks The remaining ticks for item usage.
     */
    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int remainingTicks) {
        CompoundTag tag = itemStack.getTag();
         tag.putFloat("CustomModelData", 0);
        itemStack.setTag(tag);
    }

    /**
     * Gets the maximum duration for which the gun item can be used.
     *
     * @param itemStack The ItemStack representing the gun item.
     * @return The maximum duration for item usage.
     */
    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 72000;
    }

    /**
     * Gets the animation type used while using the gun item.
     *
     * @param itemStack The ItemStack representing the gun item.
     * @return The UseAnim representing the use animation.
     */
    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }
}
