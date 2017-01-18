package net.rlse.robsmod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.rlse.robsmod.block.BlockSedimentary.EnumSedimentary;

public class ItemBlockSedimentary extends ItemBlock {

	public ItemBlockSedimentary(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
		setRegistryName(block.getRegistryName());
	}
	
	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return super.getUnlocalizedName() + "." + EnumSedimentary.Values()[itemStack.getMetadata()].getName();
	}
}
