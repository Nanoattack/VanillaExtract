package com.nano.extract.data.client;

import com.nano.extract.ModMain;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ModMain.MOD_ID, existingFileHelper);
    }
@Override
    protected void registerModels() {
    withExistingParent("juicer", modLoc("block/juicer"));
    withExistingParent("bamboo_thatch", modLoc("block/bamboo_thatch"));
    withExistingParent("tiled_bamboo_thatch", modLoc("block/tiled_bamboo_thatch"));
    withExistingParent("stripped_bamboo_thatch", modLoc("block/stripped_bamboo_thatch"));
    withExistingParent("bamboo_thatch_stairs", modLoc("block/bamboo_thatch_stairs"));
    withExistingParent("bamboo_thatch_slab", modLoc("block/bamboo_thatch_slab"));
    withExistingParent("bamboo_thatch_fence_gate", modLoc("block/bamboo_thatch_fence_gate"));
    withExistingParent("bamboo_thatch_fence", modLoc("block/bamboo_thatch_fence_inventory"));
    withExistingParent("bamboo_thatch_button", modLoc("block/bamboo_thatch_button_inventory"));
    withExistingParent("bamboo_thatch_pressure_plate", modLoc("block/bamboo_thatch_pressure_plate"));
    withExistingParent("bamboo_thatch_trapdoor", modLoc("block/bamboo_thatch_trapdoor_bottom"));

    ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
    // General

    builder(itemGenerated, "vanilla_flower");
    builder(itemGenerated, "cactus_fruit");
    builder(itemGenerated, "cactus_fruit_juice");
    builder(itemGenerated, "carrot_juice");
    builder(itemGenerated, "apple_juice");
    builder(itemGenerated, "melon_juice");
    builder(itemGenerated, "sweet_berry_juice");
    builder(itemGenerated, "chorus_fruit_juice");
    builder(itemGenerated, "milk_cup");
    builder(itemGenerated, "chocolate_milk");
    builder(itemGenerated, "banana");
    builder(itemGenerated, "apple_pie");
    builder(itemGenerated, "sweet_berry_pie");
    builder(itemGenerated, "banana_bread");
    builder(itemGenerated, "sheperds_pie");
    builder(itemGenerated, "chicken_pot_pie");
    builder(itemGenerated, "squid_ink_pasta");
    builder(itemGenerated, "pumpkin_soup");
    builder(itemGenerated, "sweet_berry_cookie");
    builder(itemGenerated, "bamboo_cup");
    builder(itemGenerated, "chicken_noodle_soup");
    builder(itemGenerated, "fish_soup");
    builder(itemGenerated, "salmon_sushi");
    builder(itemGenerated, "rice_shoots");
    builder(itemGenerated, "stuffed_mushroom");
    builder(itemGenerated, "rice_bowl");
    builder(itemGenerated, "ultimate_cocktail");
    builder(itemGenerated, "cashew_apple");
    builder(itemGenerated, "cashew_nut");
    builder(itemGenerated, "elder_guardian_spike");
    builder(itemGenerated, "cashew_milk_cup");
}

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
