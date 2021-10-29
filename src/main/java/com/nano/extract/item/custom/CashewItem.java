package com.nano.extract.item.custom;

import com.nano.extract.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class CashewItem extends Item
{

    public CashewItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        super.finishUsingItem(stack, world, entity);
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!world.isClientSide) {
                    }

        if (stack.isEmpty()) {
            return new ItemStack(ModItems.CASHEW.get());
        } else {
            if (entity instanceof PlayerEntity && !((PlayerEntity)entity).abilities.instabuild) {
                ItemStack itemstack = new ItemStack(ModItems.CASHEW.get());
                PlayerEntity playerentity = (PlayerEntity)entity;
                if (!playerentity.inventory.add(itemstack)) {
                    playerentity.drop(itemstack, false);
                }
            }

            return stack;
        }
    }

    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.EAT;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_EAT;
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        return DrinkHelper.useDrink(world, playerEntity, hand);
    }
}
