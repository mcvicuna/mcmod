package net.rlse.robsmod.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.rlse.robsmod.RobsMod;

public class RobsModTab extends CreativeTabs {

	public RobsModTab() {
		super(RobsMod.modId);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getByNameOrId("apple"));
	}
	
	@Override
	public boolean hasSearchBar() {
		return true;
	}

}
