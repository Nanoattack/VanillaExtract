package com.nano.extract;

import com.google.common.collect.ImmutableMap;
import com.nano.extract.block.ModBlocks;
import com.nano.extract.container.ModContainers;
import com.nano.extract.data.recipes.ModRecipeTypes;
import com.nano.extract.events.AcaciaLeavesConverterModifier;
import com.nano.extract.events.JungleLeavesConverterModifier;
import com.nano.extract.events.LootInjector;
import com.nano.extract.events.ModEvents;
import com.nano.extract.item.ModItems;
import com.nano.extract.screen.JuicerScreen;
import com.nano.extract.tileentity.ModTileEntities;
import com.nano.extract.world.structure.ModStructures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModMain.MOD_ID)
public class ModMain
{
    public static final String MOD_ID = "extract";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public ModMain() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);
        ModStructures.register(eventBus);
        ModRecipeTypes.register(eventBus);

        MinecraftForge.EVENT_BUS.register(new JungleLeavesConverterModifier.Serializer());
        MinecraftForge.EVENT_BUS.register(new AcaciaLeavesConverterModifier.Serializer());
        MinecraftForge.EVENT_BUS.register(new LootInjector());
        MinecraftForge.EVENT_BUS.register(new ModEvents());

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        event.enqueueWork(() ->{
            AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                    .put(ModBlocks.BAMBOO_THATCH.get(), ModBlocks.STRIPPED_BAMBOO_THATCH.get())
                    .build();
        });
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);

        ScreenManager.register(ModContainers.JUICER_CONTAINER.get(),
                JuicerScreen::new);

        RenderTypeLookup.setRenderLayer(ModBlocks.OPUNTIA_CACTUS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.RICE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BAMBOO_THATCH_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BAMBOO_THATCH_DOOR.get(), RenderType.cutout());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
        ModStructures.setupStructures();
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
