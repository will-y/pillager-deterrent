package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class PillagerDeterrentRecipes extends RecipeProvider {
    public PillagerDeterrentRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.getBannerStack(holderLookup.lookupOrThrow(Registries.BANNER_PATTERN)))
                .pattern("rgr")
                .pattern(" b ")
                .pattern("rpr")
                .define('r', Items.RED_DYE)
                .define('g', Items.GRAY_DYE)
                .define('b', Items.WHITE_BANNER)
                .define('p', Items.OMINOUS_BOTTLE)
                .unlockedBy("has_ominous_bottle", has(Items.OMINOUS_BOTTLE))
                .save(recipeOutput);
    }
}
