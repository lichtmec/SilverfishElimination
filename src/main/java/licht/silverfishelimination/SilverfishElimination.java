package licht.silverfishelimination;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySilverfish;

@Mod(modid="silverfishelimination", name="SilverfishElimination", version="1.0pre")
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

		BlockPropertyChanger.run();
	}

	@Mod.EventHandler
	public void init (FMLInitializationEvent event)
	{
		SilverfishBlockPairs.load();
	}

	@Mod.EventHandler
	public void postInit (FMLPostInitializationEvent event)
	{
	}

	public static boolean isSilverfish (Entity entity)
	{
		if (entity instanceof EntitySilverfish)
		{
			return true;
		}

		return false;
	}
}