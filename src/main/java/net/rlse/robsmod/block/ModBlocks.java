package net.rlse.robsmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.BlockSign;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.rlse.robsmod.item.IItemModelProvider;
import net.rlse.robsmod.item.ItemBlockIgneousIntrusive;
import net.rlse.robsmod.item.ItemBlockSedimentary;

public class ModBlocks {

	public static BlockOre oreCopper;
	public static BlockSedimentary blockSedimentary;
	public static ItemBlockSedimentary itemBlockSedimentary;
	public static BlockIgneousIntrusive blockIgneousIntrusive;
	public static ItemBlockIgneousIntrusive itemBlockIgneousIntrusive;
	public static BlockPathBeaten blockPathBeaten;
	public static BlockWorkZoneFarmSign blockWZSignFarm;
	public static BlockWorkZoneLumberSign blockWZSignLumber;

	/**
	 * Gotchas
	 * 
	 * Variants need to have their properties in alphanum order within the quotes in the blockstate json file
	 */
	
	public static void init() {
		oreCopper = new BlockOre("ore_copper", "ore_copper");
		register(oreCopper);
		
		blockPathBeaten = new BlockPathBeaten();
		register(blockPathBeaten);
		
		blockWZSignFarm = new BlockWorkZoneFarmSign();
		register(blockWZSignFarm);
		
		blockWZSignLumber = new BlockWorkZoneLumberSign();
		register(blockWZSignLumber);
		
		// For these, skip the local register method because it ignores meta, the proxy will register the item models
	    blockSedimentary = new BlockSedimentary();
	    GameRegistry.register(blockSedimentary);
	    System.out.println("Registered "+blockSedimentary.getRegistryName());
	    itemBlockSedimentary = new ItemBlockSedimentary(blockSedimentary);
	    GameRegistry.register(itemBlockSedimentary);
	    System.out.println("Registered Item "+itemBlockSedimentary.getRegistryName());
	    
	    blockIgneousIntrusive = new BlockIgneousIntrusive();
	    GameRegistry.register(blockIgneousIntrusive);
	    System.out.println("Registered "+blockIgneousIntrusive.getRegistryName());
	    itemBlockIgneousIntrusive = new ItemBlockIgneousIntrusive(blockIgneousIntrusive);
	    GameRegistry.register(itemBlockIgneousIntrusive);
	    System.out.println("Registered Item "+itemBlockIgneousIntrusive.getRegistryName());
	}
	
	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);

		System.out.println("Registered "+block.getRegistryName());
		
		if (itemBlock != null) {
			GameRegistry.register(itemBlock);
			System.out.println("Registered Item "+itemBlock.getRegistryName());
			
			if (block instanceof IItemModelProvider) {
				((IItemModelProvider)block).registerItemModel(itemBlock);
			}
		}

		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

}
