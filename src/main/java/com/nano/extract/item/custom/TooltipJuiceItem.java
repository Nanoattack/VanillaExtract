package com.nano.extract.item.custom;

import com.nano.extract.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipJuiceItem extends Item
{

    public TooltipJuiceItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag tooltipFlag) {

        list.add(new TranslationTextComponent("tooltip.extract.ultimate_cocktail"));

        super.appendHoverText(stack, world, list, tooltipFlag);
    }
    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
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
        return SoundEvents.WANDERING_TRADER_DRINK_MILK;
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        return DrinkHelper.useDrink(world, playerEntity, hand);
    }
}
