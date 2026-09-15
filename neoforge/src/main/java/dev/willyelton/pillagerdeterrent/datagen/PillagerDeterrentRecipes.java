package dev.willyelton.pillagerdeterrent.datagen;

import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import static dev.willyelton.pillagerdeterrent.Constants.getBannerStack;

public class PillagerDeterrentRecipes extends VanillaRecipeProvider {
    public PillagerDeterrentRecipes(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    protected void buildRecipes() {
        ShapedRecipeBuilder.shaped(output.lookup(Registries.ITEM), RecipeCategory.MISC, getBannerStack(output.lookup(Registries.BANNER_PATTERN)))
                .pattern("rgr")
                .pattern(" b ")
                .pattern("rpr")
                .define('r', Items.DYE.red())
                .define('g', Items.DYE.gray())
                .define('b', Items.BANNER.white())
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
}
