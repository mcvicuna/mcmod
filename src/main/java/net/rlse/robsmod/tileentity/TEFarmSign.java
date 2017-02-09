package net.rlse.robsmod.tileentity;

import net.minecraft.util.ITickable;

public class TEFarmSign extends TileEntityContainer
	implements ITickable {

	public TEFarmSign() {
		super(new ItemMarkerInventory(9));
		setCustomName("Farm Work Zone");
	}

	/**
	 * ITickable impl
	 */
	@Override
	public void update() {
		/*
		if (this.world.isRemote) {
			System.out.println("Hello, I'm a TEFarmSign!");
		}
		*/
	}
}
