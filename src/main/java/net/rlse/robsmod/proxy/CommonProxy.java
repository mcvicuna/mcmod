package net.rlse.robsmod.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.rlse.robsmod.block.ModBlocks;
import net.rlse.robsmod.events.EventBreakSpeed;
import net.rlse.robsmod.events.EventHarvestCheck;
import net.rlse.robsmod.item.ModItems;
import net.rlse.robsmod.recipe.ModRecipes;

public class CommonProxy {
	
	public void preInit() {
		ModBlocks.init();
		ModItems.init();
		
		MinecraftForge.EVENT_BUS.register(new EventBreakSpeed());
		MinecraftForge.EVENT_BUS.register(new EventHarvestCheck());
	}
	
	public void init() {
		ModRecipes.init();
	}
	
	public void postInit() {
	}
	
	public void registerItemRenderer(Item item, int meta, String id) {
		// Only used by ClientProxy
	}
}
