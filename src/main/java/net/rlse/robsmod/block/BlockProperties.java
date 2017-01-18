package net.rlse.robsmod.block;

import net.minecraft.block.material.Material;

public abstract class BlockProperties extends BlockBase {

	public BlockProperties(String unlocalizedName, Material mat) {
		this(unlocalizedName, mat, 0.3f, 0.7f);
	}
	
	public BlockProperties(String unlocalizedName, Material mat, float hardness, float resistance) {
		super(unlocalizedName, mat, hardness, resistance);
	}
}
