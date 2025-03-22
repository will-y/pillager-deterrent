package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = PillagerDeterrent.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(true, new PillagerDeterrentModels(packOutput));

        event.createProvider(PillagerDeterrentRecipes.Runner::new);

        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(PillagerDeterrentBlockLootTables::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(PillagerDeterrentChestLootTables::new, LootContextParamSets.CHEST)),
                event.getLookupProvider()));

        PillagerDeterrentBlockTags blockTags = new PillagerDeterrentBlockTags(packOutput, event.getLookupProvider());
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new PillagerDeterrentItemTags(packOutput, event.getLookupProvider(), blockTags.contentsGetter()));

        generator.addProvider(true, new PillagerDeterrentGlobalLootModifiers(packOutput, event.getLookupProvider()));
    }
}
