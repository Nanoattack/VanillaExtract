package com.nano.extract.data.client;

import com.nano.extract.ModMain;
import com.nano.extract.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper)
    {
        super(gen, ModMain.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
//       simpleBlock(ModBlocks.EXAMPLEBLOCK.get());
        simpleBlock(ModBlocks.BAMBOO_THATCH.get());
        simpleBlock(ModBlocks.TILED_BAMBOO_THATCH.get());
        simpleBlock(ModBlocks.STRIPPED_BAMBOO_THATCH.get());
//        logBlock(ModBlocks.GREENHEART_LOG.get());
//        logBlock(ModBlocks.STRIPPED_GREENHEART_LOG.get());
    }
}



