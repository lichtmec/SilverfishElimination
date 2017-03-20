package licht.silverfishelimination.asm;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SEClassVisitor_TileTransform extends ClassVisitor
{
	public static final String TARGET_METHOD_NAME = "updateEntity";
	public static final String TARGET_METHOD_NAME_OBF = "func_145845_h";

	public SEClassVisitor_TileTransform (ClassVisitor classVisitor)
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

		if (ASMUtil.mapMethodName(SEClassTransformer.TARGET_CLASS_PATH_TILETRANSFORM, methodName, desc).equals(TARGET_METHOD_NAME) ||
			ASMUtil.mapMethodName(SEClassTransformer.TARGET_CLASS_PATH_TILETRANSFORM, methodName, desc).equals(TARGET_METHOD_NAME_OBF))
		{
			result = true;
		}

		return result;
	}

	public static class SEMethodVisitor extends MethodVisitor
	{
		private static final String SUPER_CLASS_PATH = "net.minecraft.tileentity.TileEntity";

		public SEMethodVisitor (MethodVisitor methodVisitor)
		{
			super(Opcodes.ASM4, methodVisitor);
		}

		@Override
		public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf)
		{
			super.visitMethodInsn(opcode, owner, name, desc, itf);

			if (ASMUtil.mapMethodName(SUPER_CLASS_PATH, name, desc).equals(TARGET_METHOD_NAME) || ASMUtil.mapMethodName(SUPER_CLASS_PATH, name, desc).equals(TARGET_METHOD_NAME_OBF))
			{
				SELoadingPlugin.SE_ASM_LOGGER.info("Add hook to MobSpawner.updateEntity");

				String updateEntity_Desc;
				if (owner.contains("/"))
				{
					updateEntity_Desc = "(Lnet/minecraft/tileentity/TileEntityMobSpawner;)V";
				}
				else
				{
					updateEntity_Desc = "(L" + ASMUtil.unmapClassName("net.minecraft.tileentity.TileEntityMobSpawner") + ";)V";
				}

				super.visitVarInsn(Opcodes.ALOAD, 0);
				super.visitMethodInsn(Opcodes.INVOKESTATIC,
					"licht/silverfishelimination/asm/SEClassVisitor_TileTransform$SEMethodVisitor", "onMobSpawnerUpdated",
					updateEntity_Desc, false);
			}
		}

		public static void onMobSpawnerUpdated (TileEntityMobSpawner tileEntityMobSpawner)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			{
				tileEntityMobSpawner.writeToNBT(nbt);
			}

			if (nbt.getString("EntityId").equals("Silverfish"))
			{
				World world = tileEntityMobSpawner.getWorldObj();
				int posX = tileEntityMobSpawner.xCoord;
				int posY = tileEntityMobSpawner.yCoord;
				int posZ = tileEntityMobSpawner.zCoord;

				world.removeTileEntity(posX, posY, posZ);
				world.setBlock(posX, posY, posZ, Blocks.air);
			}
		}
	}
}