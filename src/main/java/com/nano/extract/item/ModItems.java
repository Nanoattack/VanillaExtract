package com.nano.extract.item;

import com.nano.extract.ModMain;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);


    public static final RegistryObject<Item> CACTUS_FRUIT = ITEMS.register("cactus_fruit",
            () -> new Item(new Item.Properties()
                    .food((new Food.Builder().nutrition(1).saturationMod(0.1f).fast().build()))
                    .tab(ItemGroup.TAB_FOOD)));


    public static void register(IEventBus eventBus)
    {
         ITEMS.register(eventBus);
    }
}
