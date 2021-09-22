package com.nano.extract.world;

import com.nano.extract.ModMain;
import com.nano.extract.world.gen.ModFlowerGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
                ModFlowerGeneration.generateFlowers(event);
    }
}