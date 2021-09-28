package com.nano.extract.world.structure;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nano.extract.ModMain;
import com.nano.extract.world.structure.structures.Domis_Mactibilis_Structure;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class ModStructures {
    public static final DeferredRegister<Structure<?>> STRUCTURES =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ModMain.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> DOMINIS_MACTIBILIS =
            STRUCTURES.register("domis_mactibilis", Domis_Mactibilis_Structure::new);

    /* average distance apart in chunks between spawn attempts */
    /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/
    /* this modifies the seed of the structure so no two structures always spawn over each-other.
    Make this large and unique. */
    public static void setupStructures() {
        setupMapSpacingAndLand(DOMINIS_MACTIBILIS.get(),
                new StructureSeparationSettings(100, 50, 1234567890),
                true);
        }

    /**
     * Adds the provided structure to the registry, and adds the separation settings.
     * The rarity of the structure is determined based on the values passed into
     * this method in the structureSeparationSettings argument.
     * This method is called by setupStructures above.
     **/
    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings,
                                                                       boolean transformSurroundingLand) {
        //add our structures into the map in Structure class
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        /*
         * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
         * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         *
         */
        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();


        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap =
                    settings.getValue().structureSettings().structureConfig();

            if (structureMap instanceof ImmutableMap) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig();

            } else {
                structureMap.put(structure, structureSeparationSettings);
            }
        });

    }
        public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
    }
}
