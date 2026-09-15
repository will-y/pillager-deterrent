package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import static dev.willyelton.pillagerdeterrent.Constants.rl;

public class PillagerDeterrentChestLootTables implements LootTableSubProvider {
    public static ResourceKey<LootTable> RESOURCE_KEY = ResourceKey.create(Registries.LOOT_TABLE, rl("pillager_outpost"));
    private final LootTableSubProvider.Context output;

    public PillagerDeterrentChestLootTables(LootTableSubProvider.Context context) {
        this.output = context;
    }

    @Override
    public void run() {
        this.generate();
    }

    public void generate() {
        output.accept(RESOURCE_KEY,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Registration.PILLAGER_RING.get()))
                                .add(EmptyLootItem.emptyItem())
                        ));
    }
}
