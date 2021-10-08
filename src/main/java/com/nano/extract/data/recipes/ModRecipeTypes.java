package com.nano.extract.data.recipes;

import com.nano.extract.ModMain;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes
{
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ModMain.MOD_ID);

    public static final RegistryObject<JuicerRecipe.Serializer> JUICER_SERIALIZER
            = RECIPE_SERIALIZER.register("juicing", JuicerRecipe.Serializer::new);

    public static IRecipeType<JuicerRecipe> JUICER_RECIPE
            = new JuicerRecipe.JuicerRecipeType();

    public static void register(IEventBus eventBus)
    {
        RECIPE_SERIALIZER.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, JuicerRecipe.TYPE_ID, JUICER_RECIPE);
    }
}
