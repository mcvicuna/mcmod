package net.rlse.robsmod.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TEFarmSign extends TileEntity
	implements ITickable, IInventory {

	@Override
	public void update() {
		if (this.worldObj.isRemote) {
			System.out.println("Hello, I'm a TileEntity!");
		}
	}
}
