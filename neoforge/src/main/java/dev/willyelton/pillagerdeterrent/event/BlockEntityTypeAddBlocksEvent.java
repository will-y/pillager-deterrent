package dev.willyelton.pillagerdeterrent.event;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = PillagerDeterrent.MODID)
public class BlockEntityTypeAddBlocksEvent {
    @SubscribeEvent
    public static void handle(net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.BANNER, Registration.PILLAGER_WARDING_BANNER.get());
        event.modify(BlockEntityType.BANNER, Registration.PILLAGER_WARDING_WALL_BANNER.get());
    }
}
