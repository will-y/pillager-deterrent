package dev.willyelton.pillagerdeterrent.platform;

import dev.willyelton.pillagerdeterrent.Config;
import dev.willyelton.pillagerdeterrent.Registration;
import dev.willyelton.pillagerdeterrent.platform.services.RegistrationHelper;
import net.minecraft.world.item.Item;

public class NeoForgeRegistration implements RegistrationHelper {
    @Override
    public Item getBannerBlockItem() {
        return Registration.PILLAGER_WARDING_BANNER_BLOCK_ITEM.get();
    }

    @Override
    public int bannerRange() {
        return Config.BANNER_RANGE.get();
    }
}
