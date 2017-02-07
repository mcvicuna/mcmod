package net.rlse.robsmod.guicontainer;

import java.util.List;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ItemStackMerger {

	public static boolean mergeItemStackWithSizeLimitsAndValidity(List<Slot> inventorySlots, ItemStack stack, int startIndex, int endIndex, boolean reverse) {
		boolean success = false;
		int index = startIndex;
		
		if (reverse) {
			index = endIndex - 1;
		}
		
		Slot slot;
		ItemStack slotStack;
		
		if (stack.isStackable()) {
			while (stack.getCount() > 0 &&
					(!reverse && index < endIndex || reverse && index >= startIndex)) {
					
				slot = inventorySlots.get(index);
	            slotStack = slot.getStack();

	            if (slotStack != ItemStack.EMPTY
	            		&& slotStack.getItem() == stack.getItem()
	            		&& (!stack.getHasSubtypes() || stack.getMetadata() == slotStack.getMetadata())
	            		&& ItemStack.areItemStackTagsEqual(stack, slotStack)) {
	            	
	                int l = slotStack.getCount() + stack.getCount();
	                int maxsize = Math.min(stack.getMaxStackSize(), slot.getItemStackLimit(stack));

	                if (l <= maxsize) {
	                    stack.setCount(0);
	                    slotStack.setCount(l);
	                    slot.onSlotChanged();
	                    success = true;
	                } else if (slotStack.getCount() < maxsize) {
	                    stack.setCount(stack.getCount() - stack.getMaxStackSize() - slotStack.getCount());
	                    slotStack.setCount(stack.getMaxStackSize());
	                    slot.onSlotChanged();
	                    success = true;
	                }
	            }

	            if (reverse) {
	                --index;
	            } else {
	                ++index;
	            }
	        }
		}
		
	    if (stack.getCount() > 0) {
	        if (reverse) {
	            index = endIndex - 1;
	        } else {
	            index = startIndex;
	        }

	        while (!reverse && index < endIndex || reverse && index >= startIndex && stack.getCount() > 0) {
	            slot = (Slot)inventorySlots.get(index);
	            slotStack = slot.getStack();

	            // Forge: Make sure to respect isItemValid in the slot.
	            if (slotStack == null && slot.isItemValid(stack)) {
	                if (stack.getCount() < slot.getItemStackLimit(stack)) {
	                    slot.putStack(stack.copy());
	                    stack.setCount(0);
	                    success = true;
	                    break;
	                } else {
	                    ItemStack newstack = stack.copy();
	                    newstack.setCount(slot.getItemStackLimit(stack));
	                    slot.putStack(newstack);
	                    stack.setCount(stack.getCount() - slot.getItemStackLimit(stack));
	                    success = true;
	                }
	            }

	            if (reverse) {
	                --index;
	            } else {
	                ++index;
	            }
	        }
	    }

	    return success;
	}
}
