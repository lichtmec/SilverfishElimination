package licht.silverfishelimination;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.world.BlockEvent;

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

	@SubscribeEvent
	public void onPlaySound (PlaySoundAtEntityEvent event)
	{
		if (SilverfishElimination.isSilverfish(event.entity))
		{
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onBreakBlock (BlockEvent.BreakEvent event)
	{
		if (!event.getPlayer().capabilities.isCreativeMode && event.block instanceof BlockSilverfish)
		{
			if (!event.world.isRemote)
			{
				ItemStack normalBlockStack = SilverfishBlockPairs.getNormalBlockFromSilverfishBlock(event.block, event.blockMetadata);

				if (normalBlockStack != null)
				{
					float f = 0.7F;
					double xDiff = (double)(event.world.rand.nextFloat() * f) * (double)(1F - f) * 0.5D;
					double yDiff = (double)(event.world.rand.nextFloat() * f) * (double)(1F - f) * 0.5D;
					double zDiff = (double)(event.world.rand.nextFloat() * f) * (double)(1F - f) * 0.5D;
					EntityItem entityItem = new EntityItem(event.world, event.x + xDiff, event.y + yDiff, event.z + zDiff, normalBlockStack);
					entityItem.delayBeforeCanPickup = 10;
					event.world.spawnEntityInWorld(entityItem);
				}
			}
		}
	}
}