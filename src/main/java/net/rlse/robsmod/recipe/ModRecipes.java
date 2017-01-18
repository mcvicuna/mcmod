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
import net.rlse.robsmod.item.ModItems;

public class ModRecipes {

	public static void init() {
		List<Item> toRemove = new ArrayList<Item>();
		
		toRemove.add(Items.WOODEN_PICKAXE);
		
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
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.knappingRock), ModItems.rock, ModItems.rock);
	}
}
