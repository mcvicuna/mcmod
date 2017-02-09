package net.rlse.robsmod.guicontainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class ItemMarkerSlot extends SingleItemSlot {

	public ItemMarkerSlot(IInventory inventory, int index, int xPos, int yPos) {
		super(inventory, index, xPos, yPos);
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return false;
    }
}
