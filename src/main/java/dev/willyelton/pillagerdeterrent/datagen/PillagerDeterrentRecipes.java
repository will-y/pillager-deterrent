package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class PillagerDeterrentRecipes extends RecipeProvider {
    private static final CriterionTriggerInstance CAN_CRAFT_RING = KilledTrigger.TriggerInstance.entityKilledPlayer(new EntityPredicate.Builder().entityType(EntityTypePredicate.of(EntityType.PILLAGER)).build());

    public PillagerDeterrentRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.PILLAGER_RING.get())
                .pattern(" i ")
                .pattern("ipi")
                .pattern(" i ")
                .define('i', Items.IRON_INGOT)
                .define('p', Items.GLASS_BOTTLE)
                .unlockedBy("killed_pillager", CAN_CRAFT_RING)
                .save(recipeOutput);
    }
}
