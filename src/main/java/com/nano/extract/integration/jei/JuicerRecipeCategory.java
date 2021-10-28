package com.nano.extract.integration.jei;

import com.nano.extract.ModMain;
import com.nano.extract.block.ModBlocks;
import com.nano.extract.data.recipes.JuicerRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class JuicerRecipeCategory implements IRecipeCategory<JuicerRecipe>
{
    public final ResourceLocation UID = new ResourceLocation(ModMain.MOD_ID, "juicing");

    public final ResourceLocation TEXTURE = new ResourceLocation(ModMain.MOD_ID, "textures/gui/juicer_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public JuicerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.JUICER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends JuicerRecipe> getRecipeClass() {
        return JuicerRecipe.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.JUICER.get().getName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(JuicerRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, JuicerRecipe recipe, IIngredients ingredients) {
    recipeLayout.getItemStacks().init(0, true, 79, 30);
    recipeLayout.getItemStacks().init(1, true, 79, 52);

    recipeLayout.getItemStacks().init(2, false, 102, 42);
    recipeLayout.getItemStacks().set(ingredients);
    }
}
