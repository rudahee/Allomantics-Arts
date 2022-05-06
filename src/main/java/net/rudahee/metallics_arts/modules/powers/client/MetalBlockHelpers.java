package net.rudahee.metallics_arts.modules.powers.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class MetalBlockHelpers {

    private static final World level = Minecraft.getInstance().level;
    private final Set<BlockPos> blocks = new HashSet<>();
    private Vector3d center = null;

    public MetalBlockHelpers(BlockPos initial) {
        this.add(initial);
    }

    public MetalBlockHelpers () {

    }

    public static MetalBlockHelpers merge(MetalBlockHelpers blob1, MetalBlockHelpers blob2) {
        if (blob1 == null) {
            return blob2;
        } else if (blob2 == null) {
            return blob1;
        }

        MetalBlockHelpers blob3 = new MetalBlockHelpers();
        blob3.blocks.addAll(blob1.blocks);
        blob3.blocks.addAll(blob2.blocks);
        blob3.center = blob1.center.scale(blob1.blocks.size()).add(blob2.center.scale(blob2.blocks.size())).scale(1.0 / blob3.blocks.size());
        return blob3;
    }

    public boolean isMatch(BlockPos pos) {
        return this.blocks.stream().anyMatch(bp -> Vector3d.atCenterOf(bp).distanceTo(Vector3d.atCenterOf(pos)) <= 1.5);
    }

    public int size() {
        return this.blocks.size();
    }

    private Vector3d getCenterOfBlock(BlockPos pos) {
        try {
            return Vector3d.atLowerCornerOf(pos).add(level.getBlockState(pos).getBlockState().getShape(level, pos).bounds().getCenter());
        } catch (UnsupportedOperationException e) {
            return Vector3d.atCenterOf(pos);
        }
    }

    public boolean add(BlockPos pos) {
        pos = pos.immutable();

        if (this.blocks.add(pos)) {

            if (this.center == null) {
                this.center = getCenterOfBlock(pos);
            } else {
                int count = this.blocks.size();
                this.center = this.center.scale(count - 1).add(getCenterOfBlock(pos)).scale(1.0D / count);

            }
            return true;
        }
        return false;
    }

    public boolean isIn(BlockPos pos) {
        return this.blocks.contains(pos);
    }

    public Vector3d getCenter() {
        return this.center;
    }

}
