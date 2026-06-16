package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.Constants;
import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.special.BannerSpecialRenderer;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public class PillagerDeterrentModels extends ModelProvider {
    public PillagerDeterrentModels(PackOutput output) {
        super(output, Constants.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(Registration.PILLAGER_RING.get(), ModelTemplates.FLAT_ITEM);

        createBanner(blockModels, DyeColor.WHITE);
    }

    public void createBanner(BlockModelGenerators blockModels, DyeColor baseColor) {
        Block standAlone = Registration.PILLAGER_WARDING_BANNER.get();
        Block wall = Registration.PILLAGER_WARDING_WALL_BANNER.get();
        MultiVariant blockModel = plainVariant(ModelLocationUtils.decorateBlockModelLocation("banner"));
        Identifier itemModel = ModelLocationUtils.decorateItemModelLocation("template_banner");
        blockModels.blockStateOutput.accept(createSimpleBlock(standAlone, blockModel));
        blockModels.blockStateOutput.accept(createSimpleBlock(wall, blockModel));
        Item item = standAlone.asItem();
        blockModels.itemModelOutput
                .accept(
                        item,
                        ItemModelUtils.specialModel(
                                itemModel,
                                BannerRenderer.TRANSFORMATIONS.freeTransformations(0),
                                new BannerSpecialRenderer.Unbaked(baseColor, BannerBlock.AttachmentType.GROUND)
                        )
                );
    }
}
