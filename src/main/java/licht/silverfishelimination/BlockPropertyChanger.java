package licht.silverfishelimination;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public final class BlockPropertyChanger
{
	private BlockPropertyChanger ()
	{
	}

	public static void run ()
	{
		Blocks.monster_egg.setHardness(1.5F);
		Blocks.monster_egg.setResistance(10F);
		Blocks.monster_egg.setStepSound(Block.soundTypePiston);
	}
}