package net.rlse.robsmod.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rlse.robsmod.RobsMod;
import net.rlse.robsmod.network.RobsModGuiHandler;
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
		TEFarmSign te = (TEFarmSign)world.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(world, pos, te);
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			((TEFarmSign)world.getTileEntity(pos)).setCustomName(stack.getDisplayName());
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(RobsMod.instance, RobsModGuiHandler.TE_FARM_SIGN_GUI, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
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
