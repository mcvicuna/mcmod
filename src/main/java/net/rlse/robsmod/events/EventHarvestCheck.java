package net.rlse.robsmod.events;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rlse.robsmod.block.ModBlocks;
import net.rlse.robsmod.item.ModItems;

public class EventHarvestCheck {
	
	@SubscribeEvent
	public void harvestDropsEvent(BlockEvent.HarvestDropsEvent event) {
		float rockChance = 0f;
		Random rand = event.getWorld().rand;
		
		Block harvestedBlock = event.getState().getBlock();
		if (harvestedBlock == Blocks.DIRT) {
			rockChance = 0.1f;
		} else if (harvestedBlock == Blocks.GRASS) {
			rockChance = 0.5f;
		} else if (harvestedBlock == Blocks.LOG) {
			EntityPlayer player = event.getHarvester();
			ItemStack held = player.getHeldItem(EnumHand.MAIN_HAND);
			if (held != null && held.getItem().getToolClasses(held).contains("axe")) {
				return;
			} else {
				event.getDrops().clear();
				event.getDrops().add(new ItemStack(Items.STICK, rand.nextInt(6)+2));
				event.getDrops().add(new ItemStack(ModItems.plantFiber, rand.nextInt(3)+1));
			}
		} else if (harvestedBlock == Blocks.LEAVES) {
			if (rand.nextFloat() < 0.9f) {
				event.getDrops().add(new ItemStack(ModItems.leafPile));
			}
			if (rand.nextFloat() < 0.5f) {
				event.getDrops().add(new ItemStack(Items.STICK));
			}
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
