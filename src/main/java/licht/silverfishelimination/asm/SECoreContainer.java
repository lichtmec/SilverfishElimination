package licht.silverfishelimination.asm;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

import java.util.Arrays;

public class SECoreContainer extends DummyModContainer
{
	public SECoreContainer ()
	{
		super(new ModMetadata());

		ModMetadata metadata = this.getMetadata();
		{
			metadata.modId = "core_silverfishelimination";
			metadata.name = "core_SilverfishElimination";
			metadata.version = "1.0";
			metadata.authorList = Arrays.asList("licht");
			metadata.description = "SilverfishElimination ASM Module";
			metadata.url = "";
			metadata.credits = "";
		}

		this.setEnabledState(true);
	}

	@Override
	public boolean registerBus (EventBus bus, LoadController controller)
	{
		bus.register(this);

		return true;
	}
}