package net.rlse.robsmod.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIgneousIntrusive extends BlockBase {
	public static final PropertyEnum<EnumIgneousIntrusive> IGNEOUSINTRUSIVE_TYPE = PropertyEnum.create("igin_type", BlockIgneousIntrusive.EnumIgneousIntrusive.class);

	public BlockIgneousIntrusive() {
		super("rock_igneous_intrusive", Material.ROCK, 3f, 10f);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		EnumIgneousIntrusive[] values = EnumIgneousIntrusive.values();
		for (int e=0; e<values.length; e++) {
			list.add(new ItemStack(itemIn, 1, values[e].getID()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(IGNEOUSINTRUSIVE_TYPE, EnumIgneousIntrusive.values()[meta]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumIgneousIntrusive t = (EnumIgneousIntrusive)state.getValue(IGNEOUSINTRUSIVE_TYPE);
		return t.getID();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { IGNEOUSINTRUSIVE_TYPE } );
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
	    EnumIgneousIntrusive igInType = EnumIgneousIntrusive.Values()[meta];
	    return this.getDefaultState().withProperty(IGNEOUSINTRUSIVE_TYPE, igInType);
	}
	
	public enum EnumIgneousIntrusive implements IStringSerializable {
		DIORITE(0, "diorite"),
		GABBRO(1, "gabbro"),
		GRANITE(2, "granite");
		
		private int ID;
		private String name;
		
		private static EnumIgneousIntrusive[] __values = null; 
		
		public static EnumIgneousIntrusive[] Values() {
			if (__values == null) {
				__values = EnumIgneousIntrusive.values();
			}
			return __values;
		}
		
		private EnumIgneousIntrusive(int ID, String name) {
			this.ID = ID;
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		public int getID() {
			return ID;
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}

}
