package net.rlse.robsmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityContainer extends TileEntity
	implements IInventory {
	
	private GenericInventory inventory;
	private String customName;

	public TileEntityContainer(GenericInventory inventory) {
		this.inventory = inventory;
	}
	
	public TileEntityContainer(int slots) {
		this(slots, 64);
	}
	
	public TileEntityContainer(int slots, int stackSize) {
		inventory = new GenericInventory(slots, stackSize);
		customName = null;
	}
	
	public void setCustomName(String name) {
		customName = name;
	}
	
	@Override
	public void onLoad() {
		inventory.setTileEntityContainer(this);
	}
	
	/**
	 * IInventory impl... forward to owned container, except for naming
	 */
	@Override
	public String getName() {
		return hasCustomName() ? customName : "container.te_generic_inventory";
	}

	@Override
	public boolean hasCustomName() {
		return customName != null && !customName.equals("");
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return hasCustomName() ? new TextComponentString(getName())
				: new TextComponentTranslation(getName(), new Object[0]);
	}

	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return inventory.decrStackSize(index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inventory.removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.setInventorySlotContents(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return inventory.isUsableByPlayer(player);
	}

	@Override
	public void openInventory(EntityPlayer player) {
		inventory.openInventory(player);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		inventory.closeInventory(player);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return inventory.isItemValidForSlot(index, stack);
	}

	@Override
	public int getField(int id) {
		return inventory.getField(id);
	}

	@Override
	public void setField(int id, int value) {
		inventory.setField(id, value);
	}

	@Override
	public int getFieldCount() {
		return inventory.getFieldCount();
	}

	@Override
	public void clear() {
		inventory.clear();
	}

	@Override
	public boolean isEmpty() {
		return inventory.isEmpty();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		inventory.writeToNBT(nbt);
		if (hasCustomName()) {
			nbt.setString("CustomName", getName());
		}
		return nbt;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		inventory.readFromNBT(nbt);
		if (nbt.hasKey("CustomName")) {
			customName = nbt.getString("CustomName");
		}
	}
}
