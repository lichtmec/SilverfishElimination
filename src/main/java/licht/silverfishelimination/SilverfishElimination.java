package licht.silverfishelimination;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="silverfishelimination", name="SilverfishElimination")
public class SilverfishElimination
{
	@Mod.Instance("silverfishelimination")
	public static SilverfishElimination instance;

	@SidedProxy(clientSide="licht.silverfishelimination.client.ClientProxy", serverSide="licht.silverfishelimination.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit (FMLPreInitializationEvent event)
	{
		proxy.registerEventHandler();
	}

	@Mod.EventHandler
	public void init (FMLInitializationEvent event)
	{
	}

	@Mod.EventHandler
	public void postInit (FMLPostInitializationEvent event)
	{
	}
}