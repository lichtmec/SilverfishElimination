package licht.silverfishelimination.client;

import licht.silverfishelimination.CommonEventHandler;
import licht.silverfishelimination.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerEventHandler ()
	{
		CommonEventHandler.register();
		ClientEventHandler.register();
	}
}