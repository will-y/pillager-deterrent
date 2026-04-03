package dev.willyelton.pillagerdeterrent.tag;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PillagerDeterrentTags {
    public static final TagKey<Item> PILLAGER_WARD = ItemTags.create(PillagerDeterrent.rl("pillager_ward"));
}
