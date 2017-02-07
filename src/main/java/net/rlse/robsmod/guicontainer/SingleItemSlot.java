package net.rlse.robsmod.guicontainer;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SingleItemSlot extends Slot {

	public SingleItemSlot(IInventory inventory, int index, int xPos, int yPos) {
		super(inventory, index, xPos, yPos);
	}
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}
}
