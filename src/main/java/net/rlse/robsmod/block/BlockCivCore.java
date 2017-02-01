package net.rlse.robsmod.block;

import net.minecraft.block.material.Material;

public class BlockCivCore extends BlockBase implements ICivCore {

	public BlockCivCore(String name, Material mat) {
		super(name, mat, 1.0f, 1.0f);
	}

	/**
	 * TODO Determine how to select a particular CivCore block as the master
	 * 
	 * Subclasses will need to override this if they are multiblock structures to only allow Formed MB Structs to be masters
	 */
	public boolean isMaster() {
		return true;
	}
}
