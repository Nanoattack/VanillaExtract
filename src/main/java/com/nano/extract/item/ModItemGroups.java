package com.nano.extract.item;

import com.nano.extract.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups
    //Can't have capitals in json denotation else data generators stop functioning
{
    public static final ItemGroup MOD_FOOD_GROUP = new ItemGroup("vanillaextractfood")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.SWEET_BERRY_PIE.get());
        }
    };

    public static final ItemGroup MOD_MOB_GROUP = new ItemGroup("vanillaextractmob")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.ELDER_GUARDIAN_SPIKE.get());
        }
    };

    public static final ItemGroup MOD_BLOCK_GROUP = new ItemGroup("vanillaextractblock")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModBlocks.BAMBOO_THATCH.get());
        }
    };
}
