package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PillagerDeterrentBlockStates extends BlockStateProvider {
    public PillagerDeterrentBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PillagerDeterrent.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.PILLAGER_WARDING_BANNER.get());
    }
}
