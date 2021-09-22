package com.nano.extract.world.gen;

import com.nano.extract.block.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class ModConfiguredFeatures
{

    public static final ConfiguredFeature<?, ?> OPUNTIA_CACTUS_CONFIG = Feature.FLOWER.configured((
            new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.OPUNTIA_CACTUS.get().defaultBlockState()),
                    SimpleBlockPlacer.INSTANCE)).tries(1).build())
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE.count(1));


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key,
                                                                                 ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}