package dev.willyelton.pillagerdeterrent;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Registration {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PillagerDeterrent.MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PillagerDeterrent.MODID);


    public static final DeferredHolder<Item, Item> PILLAGER_RING = ITEMS.registerSimpleItem("pillager_ring", new Item.Properties().stacksTo(1));

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("pillager_deterrent_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pillager_deterrent"))
                    .icon(() -> new ItemStack(PILLAGER_RING.get()))
                    .displayItems((flags, output) -> {
                        output.accept(PILLAGER_RING.get());
                    }).build());

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        TABS.register(modEventBus);
    }
}
