package com.nano.extract.data;

import com.nano.extract.item.ModItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import com.nano.extract.ModMain;
import com.nano.extract.block.ModBlocks;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)

    {
        // GENERAL RECIPES
        ShapedRecipeBuilder.shaped(ModBlocks.JUICER.get())
                .define('P', Items.PISTON)
                .define('C', Items.COBBLESTONE)
                .define('G', Items.GLASS_PANE)
                .define('H', Items.HOPPER)
                .pattern("CPC")
                .pattern("CHC")
                .pattern("CGC")
                .unlockedBy("has_item", has(Items.PISTON))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.BAMBOO_CUP.get())
                .define('B', Items.BAMBOO)
                .pattern("B B")
                .pattern(" B ")
                .unlockedBy("has_item", has(Items.BAMBOO))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.MILK_CUP.get(), 8)
                .requires(ModItems.BAMBOO_CUP.get(), 8)
                .requires(Items.MILK_BUCKET, 1)
                .unlockedBy("has_item", has(ModItems.BAMBOO_CUP.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SWEET_BERRY_PIE.get(), 8)
                .requires(Items.SUGAR)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.EGG)
                .unlockedBy("has_item", has(Items.SWEET_BERRIES))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.APPLE_PIE.get())
                .requires(Items.HONEYCOMB)
                .requires(Items.APPLE)
                .requires(Items.EGG)
                .unlockedBy("has_item", has(Items.APPLE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SHEPERDS_PIE.get())
                .requires(Items.BAKED_POTATO)
                .requires(Items.COOKED_MUTTON)
                .requires(Items.EGG)
                .requires(Items.CARROT)
                .unlockedBy("has_item", has(Items.COOKED_MUTTON))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.PUMPKIN_SOUP.get())
                .requires(Items.PUMPKIN, 3)
                .requires(Items.APPLE)
                .requires(Items.BOWL)
                .unlockedBy("has_item", has(Items.PUMPKIN))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.CHICKEN_POT_PIE.get())
                .define('C', Items.COOKED_CHICKEN)
                .define('M', Items.BROWN_MUSHROOM)
                .define('c', Items.CARROT)
                .define('E', Items.EGG)
                .define('W', Items.WHEAT)
                .define('B', Items.BUCKET)
                .pattern("WEW")
                .pattern("cCM")
                .pattern(" B ")
                .unlockedBy("has_item", has(Items.COOKED_CHICKEN))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.BANANA_BREAD.get())
                .define('B', ModItems.BANANA.get())
                .define('W', Items.WHEAT)
                .pattern("WBW")
                .unlockedBy("has_item", has(ModItems.BANANA.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SWEET_BERRY_COOKIE.get())
                .define('S', Items.SWEET_BERRIES)
                .define('W', Items.WHEAT)
                .pattern("WSW")
                .unlockedBy("has_item", has(Items.SWEET_BERRIES))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.CHICKEN_NOODLE_SOUP.get())
                .requires(Items.COOKED_CHICKEN)
                .requires(Items.BROWN_MUSHROOM)
                .requires(Items.CARROT)
                .requires(Items.WHEAT)
                .requires(Items.BOWL)
                .unlockedBy("has_item", has(Items.COOKED_CHICKEN))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SQUID_INK_PASTA.get())
                .requires(Items.COOKED_SALMON)
                .requires(Items.INK_SAC)
                .requires(Items.WHEAT)
                .requires(Items.BOWL)
                .unlockedBy("has_item", has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.RICE_BOWL.get())
                .define('R', ModItems.RICE_SHOOTS.get())
                .define('B', Items.BOWL)
                .pattern("RRR")
                .pattern(" B ")
                .unlockedBy("has_item", has(ModItems.RICE_SHOOTS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.FISH_SOUP.get())
                .requires(Items.COOKED_COD)
                .requires(ModItems.RICE_SHOOTS.get())
                .requires(Items.BEETROOT)
                .requires(Items.BOWL)
                .unlockedBy("has_item", has(Items.COOKED_COD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SALMON_SUSHI.get(), 3)
                .define('R', ModItems.RICE_SHOOTS.get())
                .define('S', Items.SALMON)
                .define('K', Items.DRIED_KELP)
                .pattern("K")
                .pattern("S")
                .pattern("R")
                .unlockedBy("has_item", has(Items.SALMON))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.STUFFED_MUSHROOM.get(), 8)
                .define('M', Items.BROWN_MUSHROOM)
                .define('B', Items.MILK_BUCKET)
                .pattern("MMM")
                .pattern("MBM")
                .pattern("MMM")
                .unlockedBy("has_item", has(Items.BROWN_MUSHROOM))
                .save(consumer);

    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(ModMain.MOD_ID, path);
    }
}