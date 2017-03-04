package licht.silverfishelimination;

import net.minecraftforge.common.MinecraftForge;

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
}