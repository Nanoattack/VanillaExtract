package com.nano.extract.block.custom;

import com.nano.extract.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class RiceBlock extends CropsBlock
{
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),

            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public RiceBlock(Properties builder) {
        super(builder);
    }
    @Override
    protected IItemProvider getBaseSeedId() {
        return ModItems.RICE_SHOOTS.get();
    }

    public boolean canSurvive(BlockState blockState, IWorldReader iWorldReader, BlockPos blockPos)
    {
        BlockState blockstate = iWorldReader.getBlockState(blockPos.below());
            if (blockstate.is(Blocks.GRASS_BLOCK)|| blockstate.is(Blocks.DIRT) || blockstate.is(Blocks.COARSE_DIRT) || blockstate.is(Blocks.PODZOL) || blockstate.is(Blocks.FARMLAND)) {
                BlockPos blockpos = blockPos.below();

                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockState blockstate1 = iWorldReader.getBlockState(blockpos.relative(direction));
                    FluidState fluidstate = iWorldReader.getFluidState(blockpos.relative(direction));
                    if (fluidstate.is(FluidTags.WATER) || blockstate1.is(Blocks.FROSTED_ICE)) {
                        return true;
                    }
                }
            }

            return false;
        }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }
}
