package licht.silverfishelimination;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public final class CommonEventHandler
{
	private static CommonEventHandler instance = null;

	public static void register ()
	{
		if (instance == null)
		{
			instance = new CommonEventHandler();
			MinecraftForge.EVENT_BUS.register(instance);
		}
	}

	private CommonEventHandler ()
	{
	}

	@SubscribeEvent
	public void onEntityJoinWorld (EntityJoinWorldEvent event)
	{
		if (SilverfishElimination.isSilverfish(event.entity))
		{
			event.setCanceled(true);
		}
	}
}