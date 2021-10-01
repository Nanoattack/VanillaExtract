package com.nano.extract.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class WaterCropBlockItem extends BlockItem
{
    public WaterCropBlockItem(Block block, Properties properties)
    {
        super(block, properties);
    }

    public ActionResultType useOn(ItemUseContext context) {
        ActionResultType actionresulttype = this.place(new BlockItemUseContext(context));
        if(context.getLevel().getBlockState(context.getClickedPos().above()).getBlock().is(Blocks.WATER)) {
            BlockItemUseContext newContext = new BlockItemUseContext(context.getPlayer(),
                    context.getHand(), context.getItemInHand(),
                    new BlockRayTraceResult(context.getClickLocation(), context.getClickedFace(),
                            context.getClickedPos().relative(context.getClickedFace(), 2), true));

            actionresulttype = this.place(newContext);
        }

        return !actionresulttype.consumesAction() && this.isEdible() ? this.use(context.getLevel(),
                context.getPlayer(), context.getHand()).getResult() : actionresulttype;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

        if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemstack);
        } else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemstack);
        } else {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();
            Direction direction = blockraytraceresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);

            if (world.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemstack)) {
                this.place(new BlockItemUseContext(player, hand, itemstack, new BlockRayTraceResult(raytraceresult.getLocation(),
                        direction, blockpos1, true)));
                return ActionResult.success(itemstack);
            } else {
                System.out.println("Clicked 4");
                return ActionResult.fail(itemstack);
            }
        }
    }
}

//Thank you KaupenJoe :prayge: