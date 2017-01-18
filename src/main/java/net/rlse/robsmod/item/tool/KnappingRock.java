package net.rlse.robsmod.item.tool;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.rlse.robsmod.RobsMod;
import net.rlse.robsmod.item.IItemModelProvider;

public class KnappingRock extends ItemPickaxe 
	implements IItemModelProvider {

	private String name;
	
	public KnappingRock(ToolMaterial mat, String name) {
		super(mat);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(RobsMod.creativeTab);
		this.name = name;
	}
	
	@Override
	public void registerItemModel(Item item) {
		RobsMod.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	/**
	 * This sets the original breakSpeed
	 */
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material mat = state.getMaterial();
		if (mat == Material.ROCK) {
			return 0.5f;
		}
		return 0.1f;
	}
}
