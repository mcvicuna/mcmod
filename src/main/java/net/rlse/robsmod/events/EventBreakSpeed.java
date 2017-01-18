package net.rlse.robsmod.events;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventBreakSpeed {

	@SubscribeEvent
	public void breakSpeed(PlayerEvent.BreakSpeed event) {
		if ((event.getState() == null)
			&& (event.getEntityPlayer() == null || event.getEntityPlayer().getHeldItemMainhand() == null)) {
			// leave it as default
			System.out.println("Allowing breakspeed blockstate=" + event.getState() + ", blockpos=" + event.getPos() + ", oldspeed = " + event.getOriginalSpeed());
			return;
		}
		
		if (event.getEntityPlayer() != null) {
			ItemStack held = event.getEntityPlayer().getHeldItemMainhand();
			if (held == null) {
				Material blockMat = event.getState().getMaterial();
				//if (blockMat.isToolNotRequired()) {
				if (blockMat == Material.GROUND || blockMat == Material.GRASS) {
					event.setNewSpeed(0.05f);
					return;
				}
				System.out.println("Zeroing, no item held vs non-diggable material");
			} else {
				String[] toolClasses = {"pickaxe", "shovel", "axe"};
				Set<String> heldToolClasses = held.getItem().getToolClasses(held);
				for (String toolClass : toolClasses) {
					if (heldToolClasses.contains(toolClass)) {
						float hardness = event.getState().getBlockHardness(event.getEntityPlayer().getEntityWorld(), event.getPos());
						float str = held.getItem().getStrVsBlock(held, event.getState());
						System.out.println("StrVsBlock "+str+"/"+hardness+"; "+held.getItem().getUnlocalizedName()+" v "+event.getState().getBlock().getUnlocalizedName());
						return;
					}
				}
				System.out.println("Zeroing breakspeed for non-effective tool (classes "+heldToolClasses+")");
			}
		}
		
		System.out.print("blockstate=" + event.getState() + ", blockpos=" + event.getPos() + ", oldspeed = " + event.getOriginalSpeed()+", player="+event.getEntityPlayer());
		if (event.getEntityPlayer() != null) {
			System.out.println(", held mainhand= "+event.getEntityPlayer().getHeldItemMainhand());
		} else {
			System.out.println();
		}
		event.setNewSpeed(0f);
	}
}
