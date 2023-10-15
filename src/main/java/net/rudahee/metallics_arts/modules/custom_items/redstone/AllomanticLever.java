package net.rudahee.metallics_arts.modules.custom_items.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AllomanticLever extends LeverBlock {

    public AllomanticLever(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState p_54640_, Level p_54641_, BlockPos p_54642_, Player p_54643_, InteractionHand p_54644_, BlockHitResult p_54645_) {
        return InteractionResult.FAIL;
    }
}
