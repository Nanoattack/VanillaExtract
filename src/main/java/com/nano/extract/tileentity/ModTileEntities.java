package com.nano.extract.tileentity;

import com.nano.extract.ModMain;
import com.nano.extract.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities
{
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModMain.MOD_ID);

    public static RegistryObject<TileEntityType<JuicerTile>> JUICER_TILE =
            TILE_ENTITIES.register("juicer_tile", () -> TileEntityType.Builder.of(
                    JuicerTile::new, ModBlocks.JUICER.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
