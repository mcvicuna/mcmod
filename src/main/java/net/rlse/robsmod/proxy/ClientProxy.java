package net.rlse.robsmod.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.rlse.robsmod.RobsMod;
import net.rlse.robsmod.block.BlockIgneousIntrusive;
import net.rlse.robsmod.block.BlockSedimentary;
import net.rlse.robsmod.block.ModBlocks;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();

		registerItemRenderer(ModBlocks.itemBlockSedimentary, BlockSedimentary.EnumSedimentary.CHALK.getID(), "sed_block_chalk");
		registerItemRenderer(ModBlocks.itemBlockSedimentary, BlockSedimentary.EnumSedimentary.CHERT.getID(), "sed_block_chert");
		registerItemRenderer(ModBlocks.itemBlockSedimentary, BlockSedimentary.EnumSedimentary.CLAYSTONE.getID(), "sed_block_claystone");
		registerItemRenderer(ModBlocks.itemBlockSedimentary, BlockSedimentary.EnumSedimentary.CONGLOMERATE.getID(), "sed_block_conglomerate");
		
		registerItemRenderer(ModBlocks.itemBlockIgneousIntrusive, BlockIgneousIntrusive.EnumIgneousIntrusive.DIORITE.getID(), "igin_block_diorite");
		registerItemRenderer(ModBlocks.itemBlockIgneousIntrusive, BlockIgneousIntrusive.EnumIgneousIntrusive.GABBRO.getID(), "igin_block_gabbro");
		registerItemRenderer(ModBlocks.itemBlockIgneousIntrusive, BlockIgneousIntrusive.EnumIgneousIntrusive.GRANITE.getID(), "igin_block_granite");
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(RobsMod.modId + ":" + id, "inventory"));
		System.out.println("Registered item inventory renderer "+item.getRegistryName()+" id "+id+" meta "+meta);
	}
}
