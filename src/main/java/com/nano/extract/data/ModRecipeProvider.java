package com.nano.extract.data;

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
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

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

    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(ModMain.MOD_ID, path);
    }
}