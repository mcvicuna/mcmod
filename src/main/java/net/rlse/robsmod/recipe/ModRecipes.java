package net.rlse.robsmod.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.rlse.robsmod.item.ModItems;

public class ModRecipes {

	public static void init() {
		List<Item> toRemove = new ArrayList<Item>();
		
		toRemove.add(Items.WOODEN_AXE);
		toRemove.add(Items.STONE_AXE);
		toRemove.add(Items.IRON_AXE);
		toRemove.add(Items.GOLDEN_AXE);
		toRemove.add(Items.DIAMOND_AXE);
		
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		
		Iterator<IRecipe> iter = recipes.iterator();
				          
		while (iter.hasNext()) {
			ItemStack is = iter.next().getRecipeOutput();
			Item i = (is != null ? is.getItem() : null);
			if (toRemove.contains(i)) {
				System.out.println("Found and removed "+i.getRegistryName());
				toRemove.remove(i);
				iter.remove();
			}
		};
		
		RecipeSorter.register("Shapeless Cutting", ShapelessCuttingRecipe.class, Category.SHAPELESS, "");
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.sharpRock), ModItems.rock, ModItems.rock);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.kindling), ModItems.leafPile, Items.STICK);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.kindling), ModItems.plantFiber, Items.STICK, Items.STICK);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.plantFiberBundle), ModItems.plantFiber, ModItems.plantFiber, ModItems.plantFiber, ModItems.plantFiber);
		GameRegistry.addRecipe(new ItemStack(Items.STONE_AXE), new Object[] { "BR", "S ", 'R', new ItemStack(ModItems.sharpRock, 1, 0), 'B', ModItems.plantFiber, 'S', Items.STICK });
		GameRegistry.addRecipe(new ShapelessCuttingRecipe(new ItemStack(ModItems.plantFiber), new ItemStack(ModItems.sharpRock, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.STICK, 1)));
	}
}
