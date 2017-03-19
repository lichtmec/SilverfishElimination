package licht.silverfishelimination.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;
import java.util.logging.Logger;

public class SELoadingPlugin implements IFMLLoadingPlugin
{
	public static final Logger SE_ASM_LOGGER = Logger.getLogger("SilverfishElimination_ASM_Logger");

	@Override
	public String[] getASMTransformerClass ()
	{
		return new String[] {"licht.silverfishelimination.asm.SEClassTransformer"};
	}

	@Override
	public String getModContainerClass ()
	{
		return "licht.silverfishelimination.asm.SECoreContainer";
	}

	@Override
	public String getSetupClass ()
	{
		return null;
	}

	@Override
	public void injectData (Map<String, Object> data)
	{
	}

	@Override
	public String getAccessTransformerClass ()
	{
		return null;
	}
}