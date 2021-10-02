package com.nano.extract.item;

import com.nano.extract.ModMain;
import com.nano.extract.block.ModBlocks;
import com.nano.extract.item.custom.WaterCropBlockItem;
import com.nano.extract.item.custom.juiceItem;
import com.nano.extract.item.custom.juiceMilkItem;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
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
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> RICE_SHOOTS = ITEMS.register("rice_shoots",
            () -> new WaterCropBlockItem(ModBlocks.RICE.get(), new Item.Properties()
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties()
                    .food((new Food.Builder().nutrition(1).saturationMod(0.1f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> CACTUS_FRUIT_JUICE = ITEMS.register("cactus_fruit_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).effect(()-> new EffectInstance(Effects.FIRE_RESISTANCE, 200, 0), 1f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> APPLE_JUICE = ITEMS.register("apple_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).effect(()-> new EffectInstance(Effects.MOVEMENT_SPEED, 200, 0), 1f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> CARROT_JUICE = ITEMS.register("carrot_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).effect(()-> new EffectInstance(Effects.NIGHT_VISION, 200, 0), 1f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> MELON_JUICE = ITEMS.register("melon_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).effect(()-> new EffectInstance(Effects.GLOWING, 200, 0), 1f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> SWEET_BERRY_JUICE = ITEMS.register("sweet_berry_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).effect(()-> new EffectInstance(Effects.JUMP, 200, 0), 1f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> CHORUS_FRUIT_JUICE = ITEMS.register("chorus_fruit_juice",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).effect(()-> new EffectInstance(Effects.LEVITATION, 200, 0), 1f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> MILK_CUP = ITEMS.register("milk_cup",
            () -> new juiceMilkItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(0).saturationMod(0).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> CHOCOLATE_MILK = ITEMS.register("chocolate_milk",
            () -> new juiceItem(new Item.Properties()
                    .stacksTo(8)
                    .food((new Food.Builder().nutrition(1).saturationMod(0.3f).effect(()-> new EffectInstance(Effects.DIG_SPEED, 200, 0), 1f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> BAMBOO_CUP = ITEMS.register("bamboo_cup",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> SWEET_BERRY_PIE = ITEMS.register("sweet_berry_pie",
            () -> new Item(new Item.Properties()
                    .food((new Food.Builder().nutrition(8).saturationMod(4.8f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> APPLE_PIE = ITEMS.register("apple_pie",
            () -> new Item(new Item.Properties()
                    .food((new Food.Builder().nutrition(8).saturationMod(4.8f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static final RegistryObject<Item> SWEET_BERRY_COOKIE = ITEMS.register("sweet_berry_cookie",
            () -> new Item(new Item.Properties()
                    .food((new Food.Builder().nutrition(2).saturationMod(0.4f).build()))
                    .tab(ModItemGroups.MOD_FOOD_GROUP)));

    public static void register(IEventBus eventBus)
    {
         ITEMS.register(eventBus);
    }
}
