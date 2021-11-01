package com.nano.extract.events;

import com.nano.extract.ModMain;
import com.nano.extract.item.ModItems;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents
{
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().register(
                new JungleLeavesConverterModifier.Serializer().setRegistryName
                        (new ResourceLocation(ModMain.MOD_ID,"jungle_leaves_drop_banana"))
        );
        event.getRegistry().register(
        new AcaciaLeavesConverterModifier.Serializer().setRegistryName
                (new ResourceLocation(ModMain.MOD_ID,"acacia_leaves_drop_cashew"))
        );
    }

}




