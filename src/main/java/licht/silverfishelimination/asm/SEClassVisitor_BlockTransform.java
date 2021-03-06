package licht.silverfishelimination.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SEClassVisitor_BlockTransform extends ClassVisitor
{
	public SEClassVisitor_BlockTransform (ClassVisitor classVisitor)
	{
		super(Opcodes.ASM4, classVisitor);
	}

	@Override
	public MethodVisitor visitMethod (int access, String name, String desc, String signature, String[] exceptions)
	{
		if (isMatchTargetMethod(name, desc))
		{
			return new SEMethodVisitor(super.visitMethod(access, name, desc, signature, exceptions));
		}

		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	private boolean isMatchTargetMethod (String methodName, String desc)
	{
		boolean result = false;

		if (ASMUtil.mapMethodName(SEClassTransformer.TARGET_CLASS_PATH_BLOCKTRANSFORM, methodName, desc).equals("<init>"))
		{
			result = true;
		}

		return result;
	}

	public static class SEMethodVisitor extends MethodVisitor
	{
		public static final String FIELD_OWNER_PATH = "net.minecraft.block.material.Material";

		public SEMethodVisitor (MethodVisitor methodVisitor)
		{
			super(Opcodes.ASM4, methodVisitor);
		}

		@Override
		public void visitFieldInsn(int opcode, String owner, String name, String desc)
		{
			if (ASMUtil.mapClassName(owner).equals(FIELD_OWNER_PATH))
			{
				SELoadingPlugin.SE_ASM_LOGGER.info("ChangeMaterial(BlockSilverfish) : clay -> rock");
				if (owner.contains("/"))
				{
					super.visitFieldInsn(opcode, owner, "rock", desc);
				}
				else
				{
					super.visitFieldInsn(opcode, owner, "e", desc);
				}
			}
			else
			{
				super.visitFieldInsn(opcode, owner, name, desc);
			}
		}
	}
}