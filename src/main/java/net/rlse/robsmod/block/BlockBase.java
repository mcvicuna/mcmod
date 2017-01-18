package net.rlse.robsmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.rlse.robsmod.RobsMod;
import net.rlse.robsmod.item.IItemModelProvider;

public class BlockBase extends Block
	implements IItemModelProvider {

	private String name;
	
	/**
	 * 
	 * @param unlocalizedName
	 * @param mat
	 * @param hardness Determines value needed to reach to break the block
	 * @param resistance
	 */
	public BlockBase(String unlocalizedName, Material mat, float hardness, float resistance) {
		super(mat);
		
		name = unlocalizedName;
		
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
		setCreativeTab(RobsMod.creativeTab);
		
		setHardness(hardness);
		setResistance(resistance);
	}
	
	@Override
	public void registerItemModel(Item item) {
		RobsMod.proxy.registerItemRenderer(item, 0, name);
	}
	
	@Override
	public boolean isToolEffective(String toolType, IBlockState state) {
		System.out.println("isToolEffective "+toolType+", state="+state);
		return true;
	}
}
