package dev.willyelton.pillagerdeterrent.tag;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PillagerDeterrentTags {
    public static final TagKey<Item> PILLAGER_WARD = TagKey.create(Registries.ITEM, new ResourceLocation(PillagerDeterrent.MOD_ID, "pillager_ward"));
}
