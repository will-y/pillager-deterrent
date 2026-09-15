package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.Constants;
import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootPredicates;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static dev.willyelton.pillagerdeterrent.datagen.PillagerDeterrentChestLootTables.RESOURCE_KEY;

public class PillagerDeterrentGlobalLootModifiers extends GlobalLootModifierProvider {
    public PillagerDeterrentGlobalLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Constants.MODID);
    }

    @Override
    protected void start() {
        add("pillager_outpost",
                new AddTableLootModifier(Optional.of(Holder.direct(LootTableIdCondition.builder(BuiltInLootTables.PILLAGER_OUTPOST.identifier()).build())),
                        IGlobalLootModifier.DEFAULT_PRIORITY, RESOURCE_KEY));
    }
}
