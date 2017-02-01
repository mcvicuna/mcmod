package net.rlse.robsmod.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;

abstract public class BlockWorkZoneMarker extends BlockBase
	implements IWorkZoneMarker {

	private ArrayList<IWorkZone> workZones;
	
	public BlockWorkZoneMarker(String name, Material mat) {
		super(name, mat, 0.3f, 0.5f);
		workZones = null;
	}
	
	public List<IWorkZone> getWorkZones() {
		return workZones;
	}
	
	protected void addWorkZone(IWorkZone zone) {
		if (workZones == null) {
			workZones = new ArrayList<IWorkZone>(1);
		}
		workZones.add(zone);
	}
	
	protected void addWorkZones(List<IWorkZone> zones) {
		if (workZones == null) {
			workZones = new ArrayList<IWorkZone>(zones.size());
		}
		workZones.addAll(zones);
	}
}
