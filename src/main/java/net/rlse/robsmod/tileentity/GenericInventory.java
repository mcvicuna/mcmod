package net.rlse.robsmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class GenericInventory implements IInventory {

	private TileEntityContainer owningEntity;
	private NonNullList<ItemStack> inventory;
	private int slots;
	private int stackLimit;
	
	public GenericInventory(int slots) {
		this(slots, 64);
	}
	
	public GenericInventory(int slots, int stackLimit) {
		this.slots = slots;
		this.stackLimit = stackLimit;
		inventory = NonNullList.<ItemStack>withSize(slots, ItemStack.EMPTY);
	}

	public void setTileEntityContainer(TileEntityContainer te) {
		owningEntity = te;
	}
	
	@Override
	public String getName() {
		if (owningEntity == null) {
			System.out.println("Accessing inventory before owning TE is set");
		}
		return owningEntity.getName();
	}

	@Override
	public boolean hasCustomName() {
		if (owningEntity == null) {
			System.out.println("Accessing inventory before owning TE is set");
		}
		return owningEntity.hasCustomName();
	}

	@Override
	public ITextComponent getDisplayName() {
		if (owningEntity == null) {
			System.out.println("Accessing inventory before owning TE is set");
		}
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
		return stackLimit;
	}

	@Override
	public void markDirty() {
		if (owningEntity == null) {
			System.out.println("inventory merked dirty before owning TE is set");
		}
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
		if (owningEntity == null) {
			System.out.println("Accessing inventory before owning TE is set");
		}
		return (owningEntity.getWorld().getTileEntity(owningEntity.getPos()) == owningEntity)
				&& (player.getDistanceSq(owningEntity.getPos().add(0.5, 0.5, 0.5)) <= 64);
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return ItemStackHelper.saveAllItems(nbt, inventory);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		ItemStackHelper.loadAllItems(nbt, inventory);
	}

	protected void setSlotCount(int slots) {
		// TODO Logic for resizing inventory array
		// int oldSlots = this.slots;
		// make new array, copy stacks
		// drop excess stacks as ItemEntities
		this.slots = slots;
	}

	protected NonNullList<ItemStack> getInventory() {
		return inventory;
	}
	
	protected TileEntityContainer getTileEntityContainer() {
		if (owningEntity == null) {
			System.out.println("Accessing inventory before owning TE is set");
		}
		return owningEntity;
	}
}
