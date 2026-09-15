package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.Constants;
import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.internal.NeoForgeRecipeProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Constants.MODID)
public class DataGeneration {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(true, new PillagerDeterrentModels(packOutput));

        event.createReloadableRegistryObjects(
                new RegistrySetBuilder()
                        .add(RecipeProvider.asBootstrap(PillagerDeterrentRecipes::new))
                        .add(Registries.LOOT_TABLE, new LootTableProvider(BuiltInLootTables.all(),
                                List.of(new LootTableProvider.SubProviderEntry(PillagerDeterrentBlockLootTables::new, LootContextParamSets.BLOCK),
                                        new LootTableProvider.SubProviderEntry(PillagerDeterrentChestLootTables::new, LootContextParamSets.CHEST))))
        );


        PillagerDeterrentBlockTags blockTags = new PillagerDeterrentBlockTags(packOutput, event.getWorldLookupProvider());
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new PillagerDeterrentItemTags(packOutput, event.getWorldLookupProvider(), blockTags.contentsGetter()));

        generator.addProvider(true, new PillagerDeterrentGlobalLootModifiers(packOutput, event.getWorldLookupProvider()));
    }
}
