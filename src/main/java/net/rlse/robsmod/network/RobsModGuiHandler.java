package net.rlse.robsmod.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.rlse.robsmod.gui.GuiTEFarmSign;
import net.rlse.robsmod.guicontainer.ContainerTEFarmSign;
import net.rlse.robsmod.tileentity.TEFarmSign;

public class RobsModGuiHandler implements IGuiHandler {

	public static final int TE_FARM_SIGN_GUI = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case TE_FARM_SIGN_GUI:
				return new ContainerTEFarmSign(player.inventory, (TEFarmSign)world.getTileEntity(new BlockPos(x, y, z)));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case TE_FARM_SIGN_GUI:
				return new GuiTEFarmSign(player.inventory, (TEFarmSign)world.getTileEntity(new BlockPos(x, y, z)));
		}
		
		return null;
	}

}
