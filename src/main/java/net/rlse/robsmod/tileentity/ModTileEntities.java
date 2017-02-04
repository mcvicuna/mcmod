package net.rlse.robsmod.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities {

	public static void init() {
		GameRegistry.registerTileEntity(TEFarmSign.class, "te_farm_sign");
	}
}
