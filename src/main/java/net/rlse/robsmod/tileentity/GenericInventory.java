package net.rlse.robsmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class GenericInventory implements IInventory {

	private TileEntityContainer owningEntity;
	private NonNullList<ItemStack> inventory;
	private int slots;

	public GenericInventory(TileEntityContainer te, int slots) {
		owningEntity = te;
		this.slots = slots;
		inventory = NonNullList.<ItemStack>withSize(slots, ItemStack.EMPTY);
	}

	@Override
	public String getName() {
		return owningEntity.getName();
	}

	@Override
	public boolean hasCustomName() {
		return owningEntity.hasCustomName();
	}

	@Override
	public ITextComponent getDisplayName() {
		return owningEntity.getDisplayName();
	}

	@Override
	public int getSizeInventory() {
		return slots;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= getSizeInventory()) {
			return ItemStack.EMPTY;
		}

		return inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (getStackInSlot(index) != ItemStack.EMPTY) {
			ItemStack itemStack;

			if (getStackInSlot(index).getCount() <= count) {
				itemStack = getStackInSlot(index);
				setInventorySlotContents(index, ItemStack.EMPTY);
				markDirty();
				return itemStack;
			} else {
				itemStack = getStackInSlot(index).splitStack(count);

				if (getStackInSlot(index).getCount() <= 0) {
					setInventorySlotContents(index, ItemStack.EMPTY);
				} else {
					setInventorySlotContents(index, getStackInSlot(index));
				}

				markDirty();
				return itemStack;
			}
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = getStackInSlot(index);
		setInventorySlotContents(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= getSizeInventory()) {
			return;
		}

		if (stack != ItemStack.EMPTY && stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}

		// Convert zero-count stacks to EMPTY 
		if (stack != ItemStack.EMPTY && stack.getCount() == 0) {
			stack = ItemStack.EMPTY;
		}

		inventory.set(index, stack);
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		owningEntity.markDirty();
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// Not normally used
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// Not normally used
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// Allow everything
		return true;
	}

	/*
	 * getField, setField, getFieldCount are used for special client/server
	 * sync, not used here
	 */
	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int x = 0; x < getSizeInventory(); x++) {
			setInventorySlotContents(x, ItemStack.EMPTY);
		}
	}

	public int getSlotCount() {
		return slots;
	}

	protected void setSlotCount(int slots) {
		// TODO Logic for resizing inventory array
		// int oldSlots = this.slots;
		// make new array, copy stacks
		// drop excess stacks as ItemEntities
		this.slots = slots;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemStack : inventory) {
			if (!itemStack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return (owningEntity.getWorld().getTileEntity(owningEntity.getPos()) == owningEntity)
				&& (player.getDistanceSq(owningEntity.getPos().add(0.5, 0.5, 0.5)) <= 64);
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return ItemStackHelper.saveAllItems(nbt, inventory);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		ItemStackHelper.loadAllItems(nbt, inventory);
	}
}
