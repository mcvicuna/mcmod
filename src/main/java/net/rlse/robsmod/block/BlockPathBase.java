package net.rlse.robsmod.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class BlockPathBase extends BlockBase implements ICivGraphEdge {

	public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("north");
	public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("south");
	public static final PropertyBool CONNECTED_EAST = PropertyBool.create("east");
	public static final PropertyBool CONNECTED_WEST = PropertyBool.create("west");
	
	public BlockPathBase(String name, Material material) {
		super(name, material, 3f, 5f);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED_NORTH, Boolean.FALSE).withProperty(CONNECTED_SOUTH, Boolean.FALSE).withProperty(CONNECTED_EAST, Boolean.FALSE).withProperty(CONNECTED_WEST, Boolean.FALSE));
	}
	
	abstract public int getTier();
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		state = state.withProperty(CONNECTED_NORTH, isAdjacentBlockPath(world, pos, EnumFacing.NORTH))
					.withProperty(CONNECTED_SOUTH, isAdjacentBlockPath(world, pos, EnumFacing.SOUTH))
					.withProperty(CONNECTED_EAST, isAdjacentBlockPath(world, pos, EnumFacing.EAST))
					.withProperty(CONNECTED_WEST, isAdjacentBlockPath(world, pos, EnumFacing.WEST));
		
		return state;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_EAST, CONNECTED_WEST);
	}
	
	protected boolean isAdjacentBlockPath(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		BlockPos facingPos = pos.offset(facing);
		IBlockState state = world.getBlockState(facingPos);
		if (state.getBlock() instanceof ICivGraphEdge) {
			return true;
		}
		
		// Check for one block steps
		if (world.isAirBlock(facingPos)) {
			// On-level is air, check down
			state = world.getBlockState(facingPos.offset(EnumFacing.DOWN));
			if (state.getBlock() instanceof ICivGraphEdge) {
				return true;
			}
		} else {
			// On-level is not air, check up
			state = world.getBlockState(facingPos.offset(EnumFacing.UP));
			if (state.getBlock() instanceof ICivGraphEdge) {
				return true;
			}
		}
		
		return false;
	}
}
