package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.hybrid_metals;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.server_events.OnWorldTickEvent;
import net.rudahee.metallics_arts.modules.logic.server.server_events.on_world_tick.OnTickUtils;
import net.rudahee.metallics_arts.modules.test.ModEffects;

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
        if (OnTickUtils.activationEvery30Tick(OnWorldTickEvent.getActualTick())) {
            if (player.getFoodData().getFoodLevel()<20){
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()+1);
            }
        }
        player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("feruchemical_bendalloy_tap").get(), 10, 0, true, true));
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
        if (OnTickUtils.activationEvery30Tick(OnWorldTickEvent.getActualTick())) {
            if (!player.isCreative()){
                if (player.getFoodData().getFoodLevel()>0){
                    player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()-1);
                }
            }
        }
        player.addEffect(new MobEffectInstance(ModEffects.POWER_EFFECTS.get("feruchemical_bendalloy_storage").get(), 10, 0, true, true));
    }

    /**
     * Returns an instance of BendalloyFecuchemicHelper using a factory method pattern.
     * This method allows you to create instances of BendalloyFecuchemicHelper with a consistent interface.
     *
     * @return a Supplier that returns a new instance of BendalloyFecuchemicHelper when called
     */
    public static Supplier<? extends BendalloyFecuchemicHelper> getInstance() {
        return BendalloyFecuchemicHelper::new;
    }
}
