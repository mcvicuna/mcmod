package net.rlse.robsmod.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.rlse.robsmod.block.ModBlocks;

public class TileMBWell extends TileMultiBlock {

	private static Block[][][] BLOCK_FORM = new Block[][][] {
		// Bottom
		{
			{Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE},
			{Blocks.COBBLESTONE, Blocks.WATER, Blocks.COBBLESTONE},
			{Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE},
		},
		// Middle
		{
			{Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE},
			{Blocks.COBBLESTONE, Blocks.WATER, Blocks.COBBLESTONE},
			{Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE},
		},
		// Top
		{
			{Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE},
			{Blocks.COBBLESTONE, Blocks.WATER, Blocks.COBBLESTONE},
			{Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE},
		},
	};
	
	private static BlockPos ACTIVATION_BLOCK = new BlockPos(1, 1, 2); 
	
	@Override
	public boolean checkMultiBlockForm() {
		BlockPos myPos = getPos();
		for (int x=-1; x<2; x++) {
			for (int y=0; y<3; y++) {
				for (int z=-1; z<2; z++) {
					IBlockState blockState = getWorld().getBlockState(myPos.add(x, y, z));
					if (blockState.getBlock() != BLOCK_FORM[y][x][z]) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void setupStructure() {
		BlockPos myPos = getPos();
		for (int x=-1; x<2; x++) {
			for (int y=0; y<3; y++) {
				for (int z=-1; z<2; z++) {
					getWorld().setBlockState(myPos.add(x, y, z), ModBlocks.blockCivCoreWell.getDefaultState());
				}
			}
		}
	}
}
