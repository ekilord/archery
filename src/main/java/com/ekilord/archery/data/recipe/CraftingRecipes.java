package com.ekilord.archery.data.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class CraftingRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        recipesOverrideFarmersDelight(consumer);
        recipesBlocks(consumer);
        recipesItems(consumer);
    }

    private static void recipesOverrideFarmersDelight(Consumer<FinishedRecipe> consumer) {
        //STRAW BALE
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STRAW_BALE.get(), 1)
//                .pattern("##")
//                .pattern("##")
//                .define('#', ModItems.STRAW.get())
//                .unlockedBy("has_straw", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAW.get()))
//                .save(consumer);
    }

    private static void recipesBlocks(Consumer<FinishedRecipe> consumer) {

    }

    private static void recipesItems(Consumer<FinishedRecipe> consumer) {
        //FIBER STRING
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PrimalItems.FIBER_STRING.get(), 3)
//                .requires(PrimalItems.DRIED_PLANT_FIBER.get())
//                .requires(PrimalItems.DRIED_PLANT_FIBER.get())
//                .requires(PrimalItems.DRIED_PLANT_FIBER.get())
//                .unlockedBy("has_dried_plant_fiber", InventoryChangeTrigger.TriggerInstance.hasItems(PrimalItems.DRIED_PLANT_FIBER.get()))
//                .save(consumer);
    }
}
