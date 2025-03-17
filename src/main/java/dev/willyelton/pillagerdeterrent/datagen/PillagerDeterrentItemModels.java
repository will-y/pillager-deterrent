package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PillagerDeterrentItemModels extends ItemModelProvider {
    public PillagerDeterrentItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PillagerDeterrent.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(Registration.PILLAGER_RING.get());
    }
}
