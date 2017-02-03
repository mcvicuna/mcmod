package net.rlse.robsmod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

abstract public class TileMultiBlock extends TileEntity {

	private boolean hasMaster, isMaster;
	private int masterX, masterY, masterZ;
	
	public void updateEntity() {
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setInteger("masterX", masterX);
		data.setInteger("masterY", masterY);
		data.setInteger("masterZ", masterZ);
		data.setBoolean("hasMaster", hasMaster);
		data.setBoolean("isMaster", isMaster);
		if (hasMaster() && isMaster()) {
			// Any other values should only be saved to the master!
		}
		
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		masterX = data.getInteger("masterX");
		masterY = data.getInteger("masterY");
		masterZ = data.getInteger("masterZ");
		hasMaster = data.getBoolean("hasMaster");
		isMaster = data.getBoolean("isMaster");
		if (hasMaster() && isMaster()) {
			// Any other values should only be read by the master!
		}
	}
	
	public boolean hasMaster() {
		return hasMaster;
	}
	
	public boolean isMaster() {
		return isMaster;
	}
	
	public int getMasterX() {
		return masterX;
	}
	
	public int getMasterY() {
		return masterY;
	}
	
	public int getMasterZ() {
		return masterZ;
	}
	
	public void setHasMaster(boolean hasMaster) {
		this.hasMaster = hasMaster;
	}
	
	public void setIsMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}
	
	public void setMasterCoords(int x, int y, int z) {
		masterX = x;
		masterY = y;
		masterZ = z;
	}
	
	abstract public boolean checkMultiBlockForm();
}
