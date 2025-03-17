package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = PillagerDeterrent.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeClient(), new PillagerDeterrentItemModels(packOutput, event.getExistingFileHelper()));
    }
}
