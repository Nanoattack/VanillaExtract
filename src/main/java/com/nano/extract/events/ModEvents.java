package com.nano.extract.events;

import com.nano.extract.ModMain;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class ModEvents
{
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            event.getRegistry().register(
                    new JungleLeavesConverterModifier.Serializer().setRegistryName(new ResourceLocation(ModMain.MOD_ID,"jungle_leaves"))
            );

    }
}


