package net.rlse.robsmod.events;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rlse.robsmod.block.ModBlocks;
import net.rlse.robsmod.item.ModItems;

public class EventHarvestCheck {
	
	@SubscribeEvent
	public void harvestDropsEvent(BlockEvent.HarvestDropsEvent event) {
	    // only show event if either the test block or the test item is involved
		float rockChance = 0f;
		Random rand = event.getWorld().rand;
		
		Block harvestedBlock = event.getState().getBlock();
		if (harvestedBlock == Blocks.DIRT) {
			rockChance = 0.1f;
		} else if (harvestedBlock == Blocks.GRASS) {
			rockChance = 0.5f;
		} else if (harvestedBlock == ModBlocks.blockIgneousIntrusive) {
			event.getDrops().clear();
			if (rand.nextFloat() < 0.25f) {
				event.getDrops().add(new ItemStack(ModItems.boulderGabbro));
				if (rand.nextFloat() < 0.25f) {
					event.getDrops().add(new ItemStack(ModItems.rock));
				}
			} else {
				int count;
				float chance = rand.nextFloat();
				if (chance < 0.5f) {
					count = 1;
				} else if (chance < 0.75f) {
					count = 2;
				} else if (chance < 0.875f) {
					count = 3;
				} else {
					count = 4;
				}
				event.getDrops().add(new ItemStack(ModItems.rock, count));
				if (count < 4) {
					event.getDrops().add(new ItemStack(ModItems.gravelPile, 4 - count));
				}
			}
		} else if (harvestedBlock == ModBlocks.blockSedimentary) {
			event.getDrops().clear();
			if (rand.nextFloat() < 0.1f) {
				event.getDrops().add(new ItemStack(ModItems.boulderClaystone));
				if (rand.nextFloat() < 0.1f) {
					event.getDrops().add(new ItemStack(ModItems.rock));
				}
			} else {
				int count;
				float chance = rand.nextFloat();
				if (chance < 0.5f) {
					count = 0;
					if (rand.nextFloat() < 0.25f) {
						event.getDrops().add(new ItemStack(ModItems.gravelPile));
					}
				} else if (chance < 0.875f) {
					count = 1;
				} else {
					count = 2;
				}
				
				event.getDrops().add(new ItemStack(ModItems.rock, count));
			}
		}
		
		if (rockChance > 0) {
			event.getDrops().clear();
			if (rand.nextFloat() < rockChance) {
				event.getDrops().add(new ItemStack(ModItems.rock));
			}
		}
	}
}
