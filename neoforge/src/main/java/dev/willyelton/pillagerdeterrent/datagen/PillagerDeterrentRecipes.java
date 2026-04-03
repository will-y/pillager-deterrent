package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
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
    public PillagerDeterrentRecipes(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, Registration.getBannerStack(registries.lookupOrThrow(Registries.BANNER_PATTERN)))
                .pattern("rgr")
                .pattern(" b ")
                .pattern("rpr")
                .define('r', Items.RED_DYE)
                .define('g', Items.GRAY_DYE)
                .define('b', Items.WHITE_BANNER)
                .define('p', Items.OMINOUS_BOTTLE)
                .unlockedBy("has_ominous_bottle", has(Items.OMINOUS_BOTTLE))
                .save(output);

        shaped(RecipeCategory.MISC, Registration.PILLAGER_RING.get())
                .pattern(" i ")
                .pattern("ipi")
                .pattern(" i ")
                .define('i', Items.IRON_INGOT)
                .define('p', Items.OMINOUS_BOTTLE)
                .unlockedBy("has_ominous_bottle", has(Items.OMINOUS_BOTTLE))
                .save(output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new PillagerDeterrentRecipes(provider, output);
        }

        @Override
        public String getName() {
            return PillagerDeterrent.MODID + ":recipes";
        }
    }
}
