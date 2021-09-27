package com.nano.extract.events;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class JungleLeavesConverterModifier extends LootModifier
{
    private final int countToConvert;
    private final Item itemToCheck;
    private final Item itemReward;

    public JungleLeavesConverterModifier(ILootCondition[] conditionsIn, int count, Item itemCheck, Item reward) {
        super(conditionsIn);
        countToConvert = count;
        itemToCheck = itemCheck;
        itemReward = reward;
}

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        //
        // Additional conditions can be checked, though as much as possible should be parameterized via JSON data.
        // It is better to write a new ILootCondition implementation than to do things here.
        //
        int count = 0;
        for (ItemStack stack : generatedLoot) {
            if (stack.getItem() == itemToCheck)
                count+=stack.getCount();
        }
        if (count >= countToConvert) {
            generatedLoot.removeIf(x -> x.getItem() == itemToCheck);
            generatedLoot.add(new ItemStack(itemReward, (count/countToConvert)));
            count = count%countToConvert;
            if (count > 0)
                generatedLoot.add(new ItemStack(itemToCheck, count));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<JungleLeavesConverterModifier> {

        @Override
        public JungleLeavesConverterModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            int count = JSONUtils.getAsInt(object, "count");
            Item stick = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getAsString(object, "item"))));
            Item banana = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "replacement")));
            return new JungleLeavesConverterModifier(conditionsIn, count, stick, banana);
        }

        @Override
        public JsonObject write(JungleLeavesConverterModifier instance) {
            return null;
        }
    }
}