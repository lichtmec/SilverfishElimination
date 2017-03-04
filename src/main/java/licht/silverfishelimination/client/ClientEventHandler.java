package licht.silverfishelimination.client;

import net.minecraftforge.common.MinecraftForge;

public final class ClientEventHandler
{
	private static ClientEventHandler instance = null;

	public static void register ()
	{
		if (instance == null)
		{
			instance = new ClientEventHandler();
			MinecraftForge.EVENT_BUS.register(instance);
		}
	}

	private ClientEventHandler ()
	{
	}
}