package licht.silverfishelimination;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public final class SilverfishBlockPairs
{
	private static final List<SilverfishBlockPair> SILVERFISHBLOCK_PAIR_LIST = new ArrayList<>();

	private SilverfishBlockPairs ()
	{
	}

	public static void load ()
	{
		addSilverfishBlock(Blocks.monster_egg, 0, Blocks.stone, 0);
		addSilverfishBlock(Blocks.monster_egg, 1, Blocks.cobblestone, 0);
		addSilverfishBlock(Blocks.monster_egg, 2, Blocks.stonebrick, 0);
		addSilverfishBlock(Blocks.monster_egg, 3, Blocks.stonebrick, 1);
		addSilverfishBlock(Blocks.monster_egg, 4, Blocks.stonebrick, 2);
		addSilverfishBlock(Blocks.monster_egg, 5, Blocks.stonebrick, 3);
	}

	public static void addSilverfishBlock (Block silverfishBlock, Block normalBlock, int normalBlockMetadata)
	{
		SILVERFISHBLOCK_PAIR_LIST.add(new SilverfishBlockPair(silverfishBlock, SilverfishBlockPair.WILDCARD_VALUE, normalBlock, normalBlockMetadata));
	}

	public static void addSilverfishBlock (Block silverfishBlock, int silverfishBlockMetadata, Block normalBlock, int normalBlockMetadata)
	{
		SILVERFISHBLOCK_PAIR_LIST.add(new SilverfishBlockPair(silverfishBlock, silverfishBlockMetadata, normalBlock, normalBlockMetadata));
	}

	public static ItemStack getNormalBlockFromSilverfishBlock (Block block, int metadata)
	{
		ItemStack result = null;

		for (SilverfishBlockPair silverfishBlockPair : SILVERFISHBLOCK_PAIR_LIST)
		{
			if (silverfishBlockPair.isMatch(block, metadata))
			{
				result = new ItemStack(silverfishBlockPair.getNormalBlock(), 1, silverfishBlockPair.getNormalBlockMetadata());
			}
		}

		return result;
	}

	private static final class SilverfishBlockPair
	{
		public static final int WILDCARD_VALUE = Short.MAX_VALUE;

		private final Block silverfishBlock;
		private final int silverfishBlockMetadata;

		private final Block normalBlock;
		private final int normalBlockMetadata;

		public SilverfishBlockPair (Block silverfishBlock, int silverfishBlockMetadata, Block normalBlock, int normalBlockMetadata) throws NullPointerException, IllegalArgumentException
		{
			if (silverfishBlock == null || normalBlock == null)
			{
				throw new NullPointerException();
			}
			if (normalBlockMetadata == WILDCARD_VALUE)
			{
				throw new IllegalArgumentException("normalBlockMetadata == WILDCARD_VALUE");
			}

			this.silverfishBlock = silverfishBlock;
			this.silverfishBlockMetadata = silverfishBlockMetadata;

			this.normalBlock = normalBlock;
			this.normalBlockMetadata = normalBlockMetadata;
		}

		public boolean isMatch (Block block, int metadata)
		{
			boolean result = false;

			if (block == this.silverfishBlock)
			{
				if (this.getSilverfishBlockMetadata() == WILDCARD_VALUE)
				{
					result = true;
				}
				else if (this.getSilverfishBlockMetadata() == metadata)
				{
					result = true;
				}
			}

			return result;
		}

		public Block getNormalBlock ()
		{
			return this.normalBlock;
		}

		public int getNormalBlockMetadata ()
		{
			return  this.normalBlockMetadata;
		}

		public Block getSilverfishBlock ()
		{
			return this.silverfishBlock;
		}

		public int getSilverfishBlockMetadata ()
		{
			return this.silverfishBlockMetadata;
		}
	}
}