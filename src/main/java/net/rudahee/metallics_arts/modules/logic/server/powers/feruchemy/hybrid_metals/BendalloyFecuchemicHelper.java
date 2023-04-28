package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;
import net.rudahee.metallics_arts.modules.effects.ModEffects;
import net.rudahee.metallics_arts.utils.MathUtils;

import java.util.function.Supplier;

/**
 * Helper class containing the methods and implementations for using feruchemical Bendalloy.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class BendalloyFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Bendalloy: Increases the target player's amount of food.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tapPower(Player)
     */
    @Override
    public void tapPower(Player player) {
       // if (MathUtils.isDivisibleBy30(OnWorldTickEvent.getActualTick())) {
            if (player.getFoodData().getFoodLevel() < 20) {
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()+1);
            }
        //}

        ModEffects.giveFeruchemicalTapEffect(player, MetalTagEnum.BENDALLOY);
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Bendalloy: Decreases the target player's amount of food.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        //if (MathUtils.isDivisibleBy30(OnWorldTickEvent.getActualTick())) {
            if (!player.isCreative()){
                if (player.getFoodData().getFoodLevel()>0){
                    player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()-1);
                }
            }
        //}
        ModEffects.giveFeruchemicalStorageEffect(player,MetalTagEnum.BENDALLOY);
    }

}
