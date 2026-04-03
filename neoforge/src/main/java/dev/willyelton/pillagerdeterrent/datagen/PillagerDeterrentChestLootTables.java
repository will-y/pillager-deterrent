package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public class PillagerDeterrentChestLootTables implements LootTableSubProvider {
    public static ResourceKey<LootTable> RESOURCE_KEY = ResourceKey.create(Registries.LOOT_TABLE, PillagerDeterrent.rl("pillager_outpost"));

    public PillagerDeterrentChestLootTables(HolderLookup.Provider lookupProvider) {

    }
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(RESOURCE_KEY,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Registration.PILLAGER_RING.get()))
                                .add(EmptyLootItem.emptyItem())
                        ));

    }
}
