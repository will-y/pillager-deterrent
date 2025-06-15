package dev.willyelton.pillagerdeterrent.datagen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class AddTableLootModifier extends LootModifier {
    public static final Codec<AddTableLootModifier> CODEC = RecordCodecBuilder.create((instance) -> instance.group(IGlobalLootModifier.LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter((glm) -> glm.conditions), ResourceLocation.CODEC.fieldOf("table").forGetter(AddTableLootModifier::table)).apply(instance, AddTableLootModifier::new));
    private final ResourceLocation table;

    public AddTableLootModifier(LootItemCondition[] conditionsIn, ResourceLocation table) {
        super(conditionsIn);
        this.table = table;
    }

    public ResourceLocation table() {
        return this.table;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        LootTable lootTable = context.getResolver().getLootTable(this.table);
        lootTable.getRandomItemsRaw(context, LootTable.createStackSplitter(context.getLevel(), generatedLoot::add));
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}