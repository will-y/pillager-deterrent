package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = PillagerDeterrent.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeClient(), new PillagerDeterrentItemModels(packOutput, event.getExistingFileHelper()));

        generator.addProvider(event.includeClient(), new PillagerDeterrentRecipes(packOutput, event.getLookupProvider()));

        generator.addProvider(event.includeClient(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(PillagerDeterrentBlockLootTables::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(PillagerDeterrentChestLootTables::new, LootContextParamSets.CHEST))));

        PillagerDeterrentBlockTags blockTags = new PillagerDeterrentBlockTags(packOutput, event.getLookupProvider(), event.getExistingFileHelper());
        generator.addProvider(event.includeClient(), blockTags);
        generator.addProvider(event.includeClient(), new PillagerDeterrentItemTags(packOutput, event.getLookupProvider(), blockTags.contentsGetter(), event.getExistingFileHelper()));

        generator.addProvider(event.includeClient(), new PillagerDeterrentGlobalLootModifiers(packOutput, event.getLookupProvider()));
    }
}
