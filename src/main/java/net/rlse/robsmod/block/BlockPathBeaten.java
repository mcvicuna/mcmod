package net.rlse.robsmod.block;

import net.minecraft.block.material.Material;

public class BlockPathBeaten extends BlockPathBase {

	public BlockPathBeaten() {
		super("path_beaten", Material.GROUND);
	}
	
	@Override
	public int getTier() {
		return 0;
	}
}
