package licht.silverfishelimination.asm;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.Type;

public final class ASMUtil
{
	private ASMUtil ()
	{
	}

	/**
	 * Deobfuscate class name.
	 */
	public static String mapClassName (String className)
	{
		return FMLDeobfuscatingRemapper.INSTANCE.map(className.replace('.', '/')).replace('/', '.');
	}

	/**
	 * Obfuscate class name.
	 */
	public static String unmapClassName (String className)
	{
		return FMLDeobfuscatingRemapper.INSTANCE.unmap(className.replace('.', '/')).replace('/', '.');
	}

	/**
	 * Deobfuscate method name.
	 */
	public static String mapMethodName (String ownerClassName, String methodName, String desc)
	{
		return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(unmapClassName(ownerClassName), methodName, desc);
	}

	/**
	 * Deobfuscate field name.
	 */
	public static String mapFieldName (String ownerClassName, String fieldName, String desc)
	{
		return FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(ownerClassName, fieldName, desc);
	}

	public static String toDesc (Object type, Object... rawDesc)
	{
		StringBuilder stringBuilder = new StringBuilder("(");

		for (Object obj : rawDesc)
		{
			stringBuilder.append(toDesc(obj));
		}
		stringBuilder.append(')');
		stringBuilder.append(type);

		return stringBuilder.toString();
	}

	public static String toDesc (Object rawDesc)
	{
		String ret;

		if (rawDesc instanceof Class)
		{
			ret = Type.getDescriptor((Class<?>)rawDesc);
		}
		else if (rawDesc instanceof String)
		{
			String desc = ((String)rawDesc).replace('.', '/');

			ret = desc.matches("L.+;") ? desc : "L" + desc + ";";
		}
		else
		{
			throw new IllegalArgumentException();
		}

		return ret;
	}
}