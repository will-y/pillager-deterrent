package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

import static dev.willyelton.pillagerdeterrent.datagen.PillagerDeterrentChestLootTables.RESOURCE_LOCATION;


public class PillagerDeterrentGlobalLootModifiers extends GlobalLootModifierProvider {
    public PillagerDeterrentGlobalLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, PillagerDeterrent.MODID);
    }

    @Override
    protected void start() {
        add("pillager_outpost",
                new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.PILLAGER_OUTPOST).build()},
                        RESOURCE_LOCATION));
    }
}
