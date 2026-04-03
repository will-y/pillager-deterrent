package dev.willyelton.pillagerdeterrent.platform.services;

import net.minecraft.world.item.Item;

/**
 * Service that contains methods for all the things I need to register per modloader and then use other places.
 * Also contains configs for now.
 */
public interface RegistrationHelper {
    Item getBannerBlockItem();

    int bannerRange();
}
