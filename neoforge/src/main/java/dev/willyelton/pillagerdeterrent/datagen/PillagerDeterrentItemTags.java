package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import dev.willyelton.pillagerdeterrent.tag.PillagerDeterrentTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class PillagerDeterrentItemTags extends TagsProvider<Item> {
    public PillagerDeterrentItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, Registries.ITEM, lookupProvider, PillagerDeterrent.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.getOrCreateRawBuilder(PillagerDeterrentTags.PILLAGER_WARD)
            .addElement(Registration.PILLAGER_RING.getId());
    }
}
