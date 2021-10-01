package com.nano.extract.block;

import com.nano.extract.ModMain;
import com.nano.extract.block.custom.JuicerBlock;
import com.nano.extract.block.custom.OpuntiaCactus;
import com.nano.extract.block.custom.RiceBlock;
import com.nano.extract.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MOD_ID);

    public static final RegistryObject<Block> JUICER = registerBlock("juicer",
            ()-> new JuicerBlock(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(4f,10f)));

    public static final RegistryObject<Block> OPUNTIA_CACTUS = BLOCKS.register("opuntia_cactus",
            ()-> new OpuntiaCactus(AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.BAMBOO_SAPLING).noOcclusion()));

    public static final RegistryObject<Block> RICE = BLOCKS.register("rice_crop",
            () -> new RiceBlock(AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.CROP).noOcclusion().noCollission()));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),
                new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    }
    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}