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
                .define('B', ItemTags.BUTTONS)
                .define('R', Items.REDSTONE)
                .pattern("CBC")
                .pattern("CRC")
                .pattern("CPC")
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

        ShapelessRecipeBuilder.shapeless(ModItems.SWEET_BERRY_PIE.get())
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

        ShapelessRecipeBuilder.shapeless(ModItems.SWEET_BERRY_COOKIE.get())
                .requires(Items.SWEET_BERRIES)
                .requires(Items.WHEAT, 2)
                .unlockedBy("has_item", has(Items.SWEET_BERRIES))
                .save(consumer);
            }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(ModMain.MOD_ID, path);
    }
}