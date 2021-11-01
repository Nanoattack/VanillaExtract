package com.nano.extract.data;

import com.nano.extract.block.ModBlocks;
import com.nano.extract.block.custom.OpuntiaCactus;
import com.nano.extract.item.ModItems;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PotatoBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraftforge.fml.RegistryObject;

public class ModBlockLootTables extends BlockLootTables
{
    @Override
    protected void addTables()
    {
        dropSelf(ModBlocks.JUICER.get());

        dropSelf(ModBlocks.BAMBOO_THATCH.get());

        dropSelf(ModBlocks.TILED_BAMBOO_THATCH.get());

        dropSelf(ModBlocks.STRIPPED_BAMBOO_THATCH.get());

        this.add(ModBlocks.OPUNTIA_CACTUS.get(), (p_241171_0_) -> {
            return applyExplosionDecay(p_241171_0_, LootTable.lootTable().withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(ModBlocks.OPUNTIA_CACTUS.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OpuntiaCactus.AGE, 3))).add(ItemLootEntry.lootTableItem(ModItems.CACTUS_FRUIT.get())).apply(SetCount.setCount(RandomValueRange.between(2.0F, 3.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))).withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(ModBlocks.OPUNTIA_CACTUS.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OpuntiaCactus.AGE, 2))).add(ItemLootEntry.lootTableItem(ModItems.CACTUS_FRUIT.get())).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));});

        ILootCondition.IBuilder ilootcondition$ibuilder3 = BlockStateProperty.hasBlockStateProperties(ModBlocks.RICE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PotatoBlock.AGE, 7));
        this.add(ModBlocks.RICE.get(), applyExplosionDecay(ModBlocks.RICE.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(ItemLootEntry.lootTableItem(ModItems.RICE_SHOOTS.get()))).withPool(LootPool.lootPool().when(ilootcondition$ibuilder3).add(ItemLootEntry.lootTableItem(ModItems.RICE_SHOOTS.get()).apply(ApplyBonus.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))).withPool(LootPool.lootPool().when(ilootcondition$ibuilder3))));

    }
        @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
