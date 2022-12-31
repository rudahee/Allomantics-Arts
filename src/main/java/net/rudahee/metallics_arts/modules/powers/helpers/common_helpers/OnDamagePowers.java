package net.rudahee.metallics_arts.modules.powers.helpers.common_helpers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;
import net.rudahee.metallics_arts.modules.powers.helpers.metal_helpers.AtiumAndMalatiumHelpers;
import net.rudahee.metallics_arts.modules.powers.helpers.metal_helpers.ChromiumAndNicrosilHelpers;
import net.rudahee.metallics_arts.modules.powers.helpers.metal_helpers.GoldAndElectrumHelpers;
import net.rudahee.metallics_arts.modules.powers.helpers.metal_helpers.ZincAndBrassHelpers;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class OnDamagePowers {

    public static void onDamageAllomantic(final LivingHurtEvent event, ServerPlayer source, ServerPlayer target) {
        source.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(
                sourceCapability -> {

                    /*******************************
                     *   DAMAGE WITH - PEWTER -
                     *******************************/
                    if (sourceCapability.isBurning(MetalsNBTData.PEWTER)) {
                        float amountDamage = event.getAmount();

                        ItemStack itemInHand = source.getMainHandItem();

                        if (itemInHand.getItem() == ModItemsRegister.DUELING_STAFF.get()) {

                            amountDamage = amountDamage * (((float) itemInHand.getDamageValue() / (float) itemInHand.getMaxDamage()) * 3.2f);
                        }

                        if (itemInHand.getItem() == ModItemsRegister.CRISTAL_DAGGER.get()) {
                            if (Math.random() < 0.10d) {
                                amountDamage = amountDamage * 2;
                            }
                        }

                        if (itemInHand.getItem() == ModItemsRegister.OBSIDIAN_DAGGER.get()) {
                            if (Math.random() < 0.30d) {
                                target.addEffect(new MobEffectInstance(MobEffects.WITHER, 41, 1, true, true, false));
                            }
                        }

                        if (itemInHand.getItem() == ModItemsRegister.OBSIDIAN_AXE.get()) {
                            if (Math.random() < 0.50d) {
                                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 1, true, true, false));
                                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, true, false));
                                target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20, 1, true, true, false));
                                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 2, true, true, false));

                            }
                        }

                        if (sourceCapability.getEnhanced()) {
                            if (itemInHand.getItem() == ModItemsRegister.KOLOSS_BLADE.get()) {
                                target.setHealth(2f);
                                amountDamage = 0;
                            }
                        }
                        event.setAmount(amountDamage);
                    }

                    /*******************************
                     *   DAMAGE WITH - CHROMIUM -
                     *******************************/
                    if (sourceCapability.isBurning(MetalsNBTData.CHROMIUM)) {
                        if (target instanceof Player) {
                            ChromiumAndNicrosilHelpers.drainMetalChromium((Player) event.getEntity());
                        }
                    }


                    /*******************************
                     *   DAMAGE WITH - MALATIUM -
                     *******************************/
                    if (sourceCapability.isBurning(MetalsNBTData.MALATIUM)) {
                        if (event.getEntity() instanceof Player) {
                            GoldAndElectrumHelpers.takeDeathPosToObjetive((Player) event.getEntity());
                        }
                    }

                    /********************************
                     * DAMAGE WITH NICROSIL
                     *******************************/
                    if (sourceCapability.isBurning(MetalsNBTData.NICROSIL)) {
                        if (target instanceof Player) {
                            ChromiumAndNicrosilHelpers.changeNBTinTargetForEnhanced((Player) event.getEntity());
                        }
                    }

                    /********************************
                     * DAMAGE WITH ATIUM
                     *******************************/
                    if (target instanceof Player) {
                        target.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(targetCapability -> {
                            if (targetCapability.isBurning(MetalsNBTData.ATIUM) ){
                                if (source instanceof Player) {
                                    event.setAmount(AtiumAndMalatiumHelpers.getCalculateComplexDamage(targetCapability,sourceCapability,event.getAmount()));
                                } else {
                                    event.setAmount(AtiumAndMalatiumHelpers.getCalculateSimpleDamage(targetCapability,event.getAmount()));
                                }
                            }
                        });
                    }
                });
    }

    public static void onDamageFeruchemical(final LivingHurtEvent event, ServerPlayer source, ServerPlayer target) {

        source.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(
                sourceCapability -> {

                /*******************************
                 *   DAMAGE WITH - BRASS
                 *******************************/
                if (sourceCapability.isDecanting(MetalsNBTData.BRASS)) {
                    ZincAndBrassHelpers.addFireAspectToPlayer(event.getEntity(), 4);
                }

                target.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(
                        targetCapability -> {
                            if (targetCapability.isDecanting(MetalsNBTData.BRASS)) {
                                if (event.getSource().equals(DamageSource.FREEZE)) {
                                    event.setCanceled(true);
                                }
                            }
                        });
            });
    }
}
