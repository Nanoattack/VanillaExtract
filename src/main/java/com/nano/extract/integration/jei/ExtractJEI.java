package com.nano.extract.integration.jei;

import com.nano.extract.ModMain;
import com.nano.extract.data.recipes.JuicerRecipe;
import com.nano.extract.data.recipes.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class ExtractJEI implements IModPlugin
{

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ModMain.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new JuicerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.JUICER_RECIPE).stream()
                        .filter(r -> r instanceof JuicerRecipe).collect(Collectors.toList()),
                JuicerRecipeCategory.UID);
    }
}

