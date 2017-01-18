package net.rlse.robsmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rlse.robsmod.client.RobsModTab;
import net.rlse.robsmod.proxy.CommonProxy;

@Mod(modid = RobsMod.modId, name=RobsMod.name, version=RobsMod.version, acceptedMinecraftVersions="[1.11]")
public class RobsMod {

	public static final String modId = "robsmod";
	public static final String name = "Robs Mod";
	public static final String version = "0.1.0";
	
	@Mod.Instance(modId)
	public static RobsMod instance;
	
	@SidedProxy(serverSide = "net.rlse.robsmod.proxy.CommonProxy", clientSide="net.rlse.robsmod.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	public static final RobsModTab creativeTab = new RobsModTab();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(name + " is loading");
		proxy.preInit();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
}
