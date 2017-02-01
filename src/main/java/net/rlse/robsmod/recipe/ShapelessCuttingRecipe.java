package net.rlse.robsmod.recipe;

import java.util.Random;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.rlse.robsmod.item.tool.SharpRock;
import scala.actors.threadpool.Arrays;

public class ShapelessCuttingRecipe extends ShapelessRecipes {

	// TODO figure out where random should be, and why it's needed in the call to attemptDamageItem
	private Random random;
	
	public ShapelessCuttingRecipe(ItemStack output, ItemStack... input) {
		// TODO Find out if we can fix the type-related warning with asList, or add @SuppressWarnings
		super(output, Arrays.asList(input));
	}
	
	private ItemStack damageCutter(ItemStack stack) {
		if (stack.attemptDamageItem(1, random)) {
			return null;
		}
		
		return stack;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inventoryCrafting) {
		NonNullList<ItemStack> retList = NonNullList.<ItemStack>func_191196_a();
		
		ItemStack[] remainingItems = new ItemStack[inventoryCrafting.getSizeInventory()];
		
		for (int x=0; x<remainingItems.length; x++) {
			ItemStack itemStack = inventoryCrafting.getStackInSlot(x);
			
			if (itemStack != null && itemStack.getItem() instanceof SharpRock) {
				remainingItems[x] = damageCutter(itemStack.copy());
			} else {
				remainingItems[x] = ForgeHooks.getContainerItem(itemStack);
			}
			
			if (remainingItems[x] != null) {
				retList.add(remainingItems[x]);
			}
		}
		
		return retList;
	}
}
