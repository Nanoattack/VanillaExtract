package com.nano.extract.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups
{
    public static final ItemGroup MOD_FOOD_GROUP = new ItemGroup("VanillaExtractFoodTab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.SWEET_BERRY_PIE.get());
        }
    };
}
