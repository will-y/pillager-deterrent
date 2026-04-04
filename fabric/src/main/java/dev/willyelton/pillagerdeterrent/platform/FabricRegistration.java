package dev.willyelton.pillagerdeterrent.platform;

import dev.willyelton.pillagerdeterrent.ModItems;
import dev.willyelton.pillagerdeterrent.platform.services.RegistrationHelper;
import net.minecraft.world.item.Item;

public class FabricRegistration implements RegistrationHelper {
    @Override
    public Item getBannerBlockItem() {
        return ModItems.PILLAGER_WARDING_BANNER_ITEM;
    }

    @Override
    public int bannerRange() {
        return 128;
    }
}
