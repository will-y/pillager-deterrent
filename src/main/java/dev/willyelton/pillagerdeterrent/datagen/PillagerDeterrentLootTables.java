package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Map;
import java.util.stream.Collectors;

public class PillagerDeterrentLootTables extends VanillaBlockLoot {
    public PillagerDeterrentLootTables(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {
        createComponentSavingTable(Registration.PILLAGER_WARDING_BANNER.get(), Registration.PILLAGER_WARDING_BANNER_BLOCK_ITEM.get(), DataComponents.BANNER_PATTERNS, DataComponents.HIDE_ADDITIONAL_TOOLTIP);
        createComponentSavingTable(Registration.PILLAGER_WARDING_WALL_BANNER.get(), Registration.PILLAGER_WARDING_BANNER_BLOCK_ITEM.get(), DataComponents.BANNER_PATTERNS, DataComponents.HIDE_ADDITIONAL_TOOLTIP);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.entrySet().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(PillagerDeterrent.MODID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private void createComponentSavingTable(Block block, ItemLike item, DataComponentType<?>... dataComponents) {
        LootPoolSingletonContainer.Builder<?> lti = LootItem.lootTableItem(item);
        lti.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY));

        CopyComponentsFunction.Builder copyComponentsFunctionBuilder = CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY);

        for (DataComponentType<?> dataComponent : dataComponents) {
            copyComponentsFunctionBuilder.include(dataComponent);
        }
        lti.apply(copyComponentsFunctionBuilder);

        LootPool.Builder builder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(lti);
        add(block, LootTable.lootTable().withPool(builder));
    }
}
