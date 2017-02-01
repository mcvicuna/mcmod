package net.rlse.robsmod.block;

import net.minecraft.block.material.Material;

public class BlockWorkZoneLumberSign extends BlockWorkZoneMarker {
	
	public BlockWorkZoneLumberSign() {
		super("wz_sign_lumber", Material.WOOD);
		addWorkZone(new WorkZoneFarm("oak_wood"));
	}
}
