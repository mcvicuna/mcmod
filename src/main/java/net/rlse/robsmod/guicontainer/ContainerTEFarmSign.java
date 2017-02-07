package net.rlse.robsmod.guicontainer;

import net.minecraft.block.BlockDropper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.rlse.robsmod.tileentity.TEFarmSign;

public class ContainerTEFarmSign extends Container {

	private TEFarmSign te;
	
	public ContainerTEFarmSign(IInventory playerInv, TEFarmSign teFarmSign) {
		this.te = teFarmSign;
		
		// 3x3 grid
		for (int y=0; y<3; y++) {
			for (int x=0; x<3; x++) {
				addSlotToContainer(new Slot(teFarmSign, x+y*3, 62+x*18, 17+y*18));
			}
		}
		
		// Player inv
		for (int y=0; y<3; y++) {
			for (int x=0; x<9; x++) {
				addSlotToContainer(new Slot(playerInv, x+y*9+9, 8+x*18, 84+y*18));
			}
		}
		
		// Player hotbar
		for (int x=0; x<9; x++) {
			addSlotToContainer(new Slot(playerInv, x, 8+x*18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
	    Slot slot = inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < 9) {
	        	// TE to Player
	        	if (!mergeItemStack(current, 9, 45, true)) {
	        		return ItemStack.EMPTY;
	        	}
	        } else {
	        	// Player to TE
	        	if (!mergeItemStack(current, 0, 9, false)) {
	        		return ItemStack.EMPTY;
	        	}
	        }
	        
	        if (current.isEmpty()) {
	            slot.putStack(ItemStack.EMPTY);
	        } else {
	            slot.onSlotChanged();
	        }

	        if (current.getCount() == previous.getCount()) {
	            return ItemStack.EMPTY;
	        }
	        
	        slot.onTake(playerIn, current);
	    }
	    
	    return previous;
	}
}
