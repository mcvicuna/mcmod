package net.rlse.robsmod.block;

import java.util.List;

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

public class BlockSedimentary extends BlockBase {
	public static final PropertyEnum<EnumSedimentary> SEDIMENTARY_TYPE = PropertyEnum.create("sed_type", BlockSedimentary.EnumSedimentary.class);

	public BlockSedimentary() {
		super("rocksedimentary", Material.ROCK, 3f, 10f);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		EnumSedimentary[] values = EnumSedimentary.values();
		for (int e=0; e<values.length; e++) {
			list.add(new ItemStack(itemIn, 1, values[e].getID()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(SEDIMENTARY_TYPE, EnumSedimentary.values()[meta]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumSedimentary t = (EnumSedimentary)state.getValue(SEDIMENTARY_TYPE);
		return t.getID();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { SEDIMENTARY_TYPE } );
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
	    EnumSedimentary sedType = EnumSedimentary.Values()[meta];
	    return this.getDefaultState().withProperty(SEDIMENTARY_TYPE, sedType);
	}
	
	public enum EnumSedimentary implements IStringSerializable {
		CHALK(0, "chalk"),
		CHERT(1, "chert"),
		CLAYSTONE(2, "claystone"),
		CONGLOMERATE(3, "conglomerate");
		
		private int ID;
		private String name;
		
		private static EnumSedimentary[] __values = null; 
		
		public static EnumSedimentary[] Values() {
			if (__values == null) {
				__values = EnumSedimentary.values();
			}
			return __values;
		}
		
		private EnumSedimentary(int ID, String name) {
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
