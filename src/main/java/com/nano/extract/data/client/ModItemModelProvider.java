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

    ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
    // General

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
    builder(itemGenerated, "pumpkin_soup");
    builder(itemGenerated, "sweet_berry_cookie");
    builder(itemGenerated, "bamboo_cup");
    builder(itemGenerated, "rice_shoots");
}

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
