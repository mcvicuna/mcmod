package net.rlse.robsmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.rlse.robsmod.item.tool.KnappingRock;

public class ModItems {

	public static ItemRock rock;
	public static KnappingRock knappingRock;
	
	public static ItemBoulderGabbro boulderGabbro;
	public static ItemBoulderClaystone boulderClaystone;
	public static ItemGravelPile gravelPile;
	
	public static void init() {
		rock = register(new ItemRock("rock"));
		knappingRock = register(new KnappingRock(ToolMaterial.WOOD, "knappingrock"));
		
		boulderGabbro = register(new ItemBoulderGabbro());
		boulderClaystone = register(new ItemBoulderClaystone());
		gravelPile = register(new ItemGravelPile());
	}
	
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		System.out.println("Registered Item "+item.getRegistryName());
		
		if (item instanceof IItemModelProvider) {
			((IItemModelProvider)item).registerItemModel(item);
			System.out.println("Registered Item Model "+item.getRegistryName());
		}
		
		return item;
	}
}
