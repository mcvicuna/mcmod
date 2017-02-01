package net.rlse.robsmod.block;

import net.minecraft.block.material.Material;

public class BlockWorkZoneFarmSign extends BlockWorkZoneMarker {

	public BlockWorkZoneFarmSign() {
		super("wz_sign_farm", Material.WOOD);
		addWorkZone(new WorkZoneFarm("wheat"));
	}
}
