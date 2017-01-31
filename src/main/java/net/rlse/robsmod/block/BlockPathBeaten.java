package net.rlse.robsmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPathBeaten extends BlockPathBase {

	/**
	 * Copied from BlockGrassPath
	 */
    protected static final AxisAlignedBB PATH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
    
	public BlockPathBeaten() {
		super("path_beaten", Material.GROUND);
		this.setLightOpacity(255);
	}
	
	@Override
	public int getTier() {
		return 0;
	}
	
	/**
	 * Copied from BlockGrassPath
	 */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        switch (side) {
            case UP:
                return true;
            case NORTH:
            case SOUTH:
            case WEST:
            case EAST:
                IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
                Block block = iblockstate.getBlock();
                return !iblockstate.isOpaqueCube() && block != Blocks.FARMLAND && block != Blocks.GRASS_PATH && block != ModBlocks.blockPathBeaten;
            default:
                return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
    }
    
	/**
	 * Copied from BlockGrassPath
	 */
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
        convertToDirtIfNecessary(worldIn, pos);
    }
    
	/**
	 * Copied from BlockGrassPath
	 */
    private void convertToDirtIfNecessary(World worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos.up()).getMaterial().isSolid())
        {
        	worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
        }
    }

	/**
	 * Copied from BlockGrassPath
	 */
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return PATH_AABB;
    }

	/**
	 * Copied from BlockGrassPath
	 */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
	/**
	 * Copied from BlockGrassPath
	 */
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
	/**
	 * Copied from BlockGrassPath
	 */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Blocks.DIRT.getItemDropped(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
    }

	/**
	 * Copied from BlockGrassPath
	 */
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this);
    }
    
	/**
	 * Copied from BlockGrassPath
	 */
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos neighbor) {
        super.neighborChanged(state, worldIn, pos, blockIn, neighbor);
        convertToDirtIfNecessary(worldIn, pos);
    }
}
