package com.nano.extract.item;

import com.nano.extract.ModMain;
import com.nano.extract.block.ModBlocks;
import com.nano.extract.item.custom.juiceItem;
import net.minecraft.item.*;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);


    public static final RegistryObject<Item> CACTUS_FRUIT = ITEMS.register("cactus_fruit",
            () -> new BlockItem(ModBlocks.OPUNTIA_CACTUS.get(), new Item.Properties()
                    .food((new Food.Builder().nutrition(1).saturationMod(0.1f).build()))
                    .tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties()
                    .food((new Food.Builder().nutrition(1).saturationMod(0.1f).build()))
                    .tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> CACTUS_FRUIT_JUICE = ITEMS.register("cactus_fruit_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).build()))
                    .tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> APPLE_JUICE = ITEMS.register("apple_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).fast().build()))
                    .tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> CARROT_JUICE = ITEMS.register("carrot_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).fast().build()))
                    .tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> BAMBOO_CUP = ITEMS.register("bamboo_cup",
            () -> new Item(new Item.Properties()
                    .tab(ItemGroup.TAB_MISC)));





    public static void register(IEventBus eventBus)
    {
         ITEMS.register(eventBus);
    }
}
