package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.Constants;
import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class PillagerDeterrentBlockTags extends BlockTagsProvider {
    public PillagerDeterrentBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Constants.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
