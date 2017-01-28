package net.rlse.robsmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.rlse.robsmod.item.tool.SharpRock;

public class ModItems {

	public static ItemRock rock;
	public static ItemLeafPile leafPile;
	public static ItemPlantFiber plantFiber;
	public static ItemPlantFiberBundle plantFiberBundle;
	public static ItemKindling kindling;
	
	public static ItemBoulderGabbro boulderGabbro;
	public static ItemBoulderClaystone boulderClaystone;
	public static ItemGravelPile gravelPile;
	
	public static SharpRock sharpRock;
	
	public static void init() {
		rock = register(new ItemRock("rock"));
		leafPile = register(new ItemLeafPile("leaf_pile"));
		plantFiber = register(new ItemPlantFiber("plant_fiber"));
		plantFiberBundle = register(new ItemPlantFiberBundle("plant_fiber_bundle"));
		kindling = register(new ItemKindling("kindling"));
		
		boulderGabbro = register(new ItemBoulderGabbro());
		boulderClaystone = register(new ItemBoulderClaystone());
		gravelPile = register(new ItemGravelPile());
		
		sharpRock = register(new SharpRock(ToolMaterial.WOOD, "sharp_rock"));
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
