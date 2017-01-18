package net.rlse.robsmod.item;

import net.minecraft.item.Item;
import net.rlse.robsmod.RobsMod;

public class ItemBase extends Item
	implements IItemModelProvider {
	
	protected String name;

	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(RobsMod.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		RobsMod.proxy.registerItemRenderer(item, 0, name);
	}
}
