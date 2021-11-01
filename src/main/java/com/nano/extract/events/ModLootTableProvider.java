package com.nano.extract.events;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.nano.extract.data.ModBlockLootTables;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

//TY Pale Imitations/Brad (Dev of Schools of Magic Mod) for the code!

public class ModLootTableProvider extends LootTableProvider

{

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> loot_tables
            = ImmutableList.of(Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK));

    public ModLootTableProvider(DataGenerator dataGeneratorIn)
    {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return this.loot_tables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker)
    { }
}
