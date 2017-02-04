package net.rlse.robsmod.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rlse.robsmod.tileentity.TEFarmSign;

public class BlockWorkZoneFarmSign extends BlockWorkZoneMarker
	implements ITileEntityProvider {

	public BlockWorkZoneFarmSign() {
		super("wz_sign_farm", Material.WOOD);
		addWorkZone(new WorkZoneFarm("wheat"));
		isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEFarmSign();
	}
	
	/**
	 * Copied from BlockContainer, needed to remove the TE from the world when block is removed
	 */
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		super.breakBlock(world, pos, state);
		world.removeTileEntity(pos);
	}
	
	/**
	 * Copied from BlockContainer, needed to pass events through to the TE
	 */
	@Override
	public boolean eventReceived(IBlockState state, World world, BlockPos pos, int eventID, int eventParam) {
		super.eventReceived(state, world, pos, eventID, eventParam);
		TileEntity te = world.getTileEntity(pos);
		return te == null ? false : te.receiveClientEvent(eventID, eventParam);
	}
}
