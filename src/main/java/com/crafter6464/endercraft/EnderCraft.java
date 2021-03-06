package com.crafter6464.endercraft;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.crafter6464.endercraft.client.TabEnderCraft;
import com.crafter6464.endercraft.handler.ConfigurationHandler;
import com.crafter6464.endercraft.handler.GuiHandler;
import com.crafter6464.endercraft.init.EnderCraftBlocks;
import com.crafter6464.endercraft.init.EnderCraftItems;
import com.crafter6464.endercraft.proxy.IProxy;
import com.crafter6464.endercraft.reference.Reference;
import com.crafter6464.endercraft.utility.Log;
import com.crafter6464.endercraft.world.WorldGenerator;



@Mod(modid = Reference.MOD_ID,name = Reference.MOD_NAME,version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_PATH )

public class EnderCraft {
	
	public static TabEnderCraft endercrafttab = new TabEnderCraft();
	
	@Mod.Instance
	public static EnderCraft instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PATH, serverSide = Reference.SERVER_PATH)
	public static IProxy proxy;
	
	
@EventHandler
	public void preInit(FMLPreInitializationEvent event){
NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MOD_ID, new GuiHandler());
ConfigurationHandler.init(event.getSuggestedConfigurationFile());
FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
Log.info("PreInit finished");	
}
@EventHandler
public void init(FMLInitializationEvent event){
	
	EnderCraftItems.init();
	EnderCraftBlocks.init();
	proxy.registerRenders();
	proxy.registerTileEntities();
	GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
	Log.info("Init finished");	
}

@EventHandler
public void postInit(FMLPostInitializationEvent event){
	Log.info("Post Init finished");	
}
	
}
