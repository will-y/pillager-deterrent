package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import dev.willyelton.pillagerdeterrent.tag.PillagerDeterrentTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class PillagerDeterrentItemTags extends ItemTagsProvider {
    public PillagerDeterrentItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, PillagerDeterrent.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PillagerDeterrentTags.PILLAGER_WARD).add(Registration.PILLAGER_RING.get());
    }
}
