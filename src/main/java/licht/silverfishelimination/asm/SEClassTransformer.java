package licht.silverfishelimination.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import net.minecraft.launchwrapper.IClassTransformer;

public class SEClassTransformer implements IClassTransformer
{
	public static final String TARGET_CLASS_PATH = "net.minecraft.block.BlockSilverfish";

	@Override
	public byte[] transform (String name, String transformedName, byte[] basicClass)
	{
		byte[] bytes = basicClass;

		if (transformedName.equals(TARGET_CLASS_PATH))
		{
			ClassReader classReader = new ClassReader(basicClass);
			ClassWriter classWriter = new ClassWriter(1);
			ClassVisitor classVisitor = new SEClassVisitor(classWriter);

			classReader.accept(classVisitor, 0);

			bytes = classWriter.toByteArray();
		}

		return bytes;
	}
}