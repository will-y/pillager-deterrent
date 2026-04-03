package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;

public class PillagerDeterrentModels extends ModelProvider {
    public PillagerDeterrentModels(PackOutput output) {
        super(output, PillagerDeterrent.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(Registration.PILLAGER_RING.get(), ModelTemplates.FLAT_ITEM);

        blockModels.createBanner(Registration.PILLAGER_WARDING_BANNER.get(), Registration.PILLAGER_WARDING_WALL_BANNER.get(), DyeColor.WHITE);
    }
}
