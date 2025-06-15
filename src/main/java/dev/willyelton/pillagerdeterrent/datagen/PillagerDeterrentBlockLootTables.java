package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Map;
import java.util.stream.Collectors;

public class PillagerDeterrentBlockLootTables extends VanillaBlockLoot {

    @Override
    protected void generate() {
        createNbtSavingTable(Registration.PILLAGER_WARDING_BANNER.get(), Registration.PILLAGER_WARDING_BANNER_BLOCK_ITEM.get());
        createNbtSavingTable(Registration.PILLAGER_WARDING_WALL_BANNER.get(), Registration.PILLAGER_WARDING_BANNER_BLOCK_ITEM.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.entrySet().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(PillagerDeterrent.MODID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private void createNbtSavingTable(Block block, Item item) {
        LootPoolSingletonContainer.Builder<?> lti = LootItem.lootTableItem(item);
        lti.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY));
        lti.apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("BlockEntityTag", "BlockEntityTag", CopyNbtFunction.MergeStrategy.REPLACE));

        LootPool.Builder builder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(lti);
        add(block, LootTable.lootTable().withPool(builder));
    }
}
