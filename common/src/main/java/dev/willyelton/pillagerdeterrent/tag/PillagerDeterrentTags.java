package dev.willyelton.pillagerdeterrent.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static dev.willyelton.pillagerdeterrent.Constants.rl;

public class PillagerDeterrentTags {
    public static final TagKey<Item> PILLAGER_WARD = TagKey.create(Registries.ITEM, rl("pillager_ward"));
}
