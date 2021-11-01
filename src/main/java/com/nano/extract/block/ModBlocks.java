package com.nano.extract.block;

import com.nano.extract.ModMain;
import com.nano.extract.block.custom.JuicerBlock;
import com.nano.extract.block.custom.OpuntiaCactus;
import com.nano.extract.block.custom.RiceBlock;
import com.nano.extract.item.ModItemGroups;
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
            ()-> new OpuntiaCactus(AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.BAMBOO_SAPLING).noOcclusion().noCollission()));

    public static final RegistryObject<Block> RICE = BLOCKS.register("rice_crop",
            () -> new RiceBlock(AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.CROP).noOcclusion().noCollission()));

/*    public static final RegistryObject<Block> GREENHEART_LOG = registerBlock("greenheart_log",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> GREENHEART_WOOD = registerBlock("greenheart_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_GREENHEART_LOG = registerBlock("stripped_greenheart_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_GREENHEART_WOOD = registerBlock("stripped_greenheart_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> GREENHEART_PLANKS = registerBlock("greenheart_planks",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
*/
public static final RegistryObject<Block> BAMBOO_THATCH = registerBlock("bamboo_thatch",
        () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));

public static final RegistryObject<Block> TILED_BAMBOO_THATCH = registerBlock("tiled_bamboo_thatch",
        () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));

    public static final RegistryObject<Block> STRIPPED_BAMBOO_THATCH = registerBlock("stripped_bamboo_thatch",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));

    public static final RegistryObject<StairsBlock> BAMBOO_THATCH_STAIRS = registerBlock("bamboo_thatch_stairs",
            () -> new StairsBlock(()-> BAMBOO_THATCH.get().defaultBlockState(),
                    AbstractBlock.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.BAMBOO)));

    public static final RegistryObject<SlabBlock> BAMBOO_THATCH_SLAB = registerBlock("bamboo_thatch_slab",
            () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.OAK_SLAB).sound(SoundType.BAMBOO)));

    public static final RegistryObject<FenceBlock> BAMBOO_THATCH_FENCE = registerBlock("bamboo_thatch_fence",
            () -> new FenceBlock(AbstractBlock.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.BAMBOO)));

    public static final RegistryObject<FenceGateBlock> BAMBOO_THATCH_FENCE_GATE = registerBlock("bamboo_thatch_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.BAMBOO)));

    public static final RegistryObject<WoodButtonBlock> BAMBOO_THATCH_BUTTON = registerBlock("bamboo_thatch_button",
            () -> new WoodButtonBlock(AbstractBlock.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.BAMBOO).noCollission()));

    public static final RegistryObject<PressurePlateBlock> BAMBOO_THATCH_PRESSURE_PLATE = registerBlock("bamboo_thatch_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(Blocks.OAK_PRESSURE_PLATE).sound(SoundType.BAMBOO).noCollission()));

    public static final RegistryObject<TrapDoorBlock> BAMBOO_THATCH_TRAPDOOR = registerBlock("bamboo_thatch_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR).sound(SoundType.BAMBOO).noOcclusion()));

    public static final RegistryObject<Block> BAMBOO_THATCH_DOOR = registerBlock("bamboo_thatch_door",
            () -> new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR).sound(SoundType.BAMBOO).noOcclusion()));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroups.MOD_BLOCK_GROUP)));
    }
    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}