package licht.silverfishelimination.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import net.minecraft.launchwrapper.IClassTransformer;

public class SEClassTransformer implements IClassTransformer
{
	public static final String TARGET_CLASS_PATH_BLOCKTRANSFORM = "net.minecraft.block.BlockSilverfish";
	public static final String TARGET_CLASS_PATH_TILETRANSFORM = "net.minecraft.tileentity.TileEntityMobSpawner";

	@Override
	public byte[] transform (String name, String transformedName, byte[] basicClass)
	{
		byte[] bytes = basicClass;

		if (transformedName.equals(TARGET_CLASS_PATH_BLOCKTRANSFORM))
		{
			ClassReader classReader = new ClassReader(basicClass);
			ClassWriter classWriter = new ClassWriter(1);
			ClassVisitor classVisitor = new SEClassVisitor_BlockTransform(classWriter);

			classReader.accept(classVisitor, 0);

			bytes = classWriter.toByteArray();
		}
		else if (transformedName.equals(TARGET_CLASS_PATH_TILETRANSFORM))
		{
			ClassReader classReader = new ClassReader(basicClass);
			ClassWriter classWriter = new ClassWriter(1);
			ClassVisitor classVisitor = new SEClassVisitor_TileTransform(classWriter);

			classReader.accept(classVisitor, 0);

			bytes = classWriter.toByteArray();
		}

		return bytes;
	}
}