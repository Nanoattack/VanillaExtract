package com.nano.extract.item.custom;

import com.nano.extract.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class juiceMilkItem extends Item
{

    public juiceMilkItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity)
    {

        if (!world.isClientSide) {
            removeRandomEffect(((PlayerEntity)entity));
        }

        super.finishUsingItem(stack, world, entity);
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!world.isClientSide) {
        }

        if (stack.isEmpty()) {
            return new ItemStack(ModItems.BAMBOO_CUP.get());
        } else {
            if (entity instanceof PlayerEntity && !((PlayerEntity)entity).abilities.instabuild) {
                ItemStack itemstack = new ItemStack(ModItems.BAMBOO_CUP.get());
                PlayerEntity playerentity = (PlayerEntity)entity;
                if (!playerentity.inventory.add(itemstack)) {
                    playerentity.drop(itemstack, false);
                }
            }

            return stack;
        }
    }

    public static void removeRandomEffect(PlayerEntity player) {
        if(player.getActiveEffects().isEmpty()) {
            return;
        }
        int toRemoveIndex = random.nextInt(player.getActiveEffects().size());
        player.removeEffect(((EffectInstance)player.getActiveEffects().stream().toArray()[toRemoveIndex]).getEffect());
    }

    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.WANDERING_TRADER_DRINK_MILK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        return DrinkHelper.useDrink(world, playerEntity, hand);
    }
}
