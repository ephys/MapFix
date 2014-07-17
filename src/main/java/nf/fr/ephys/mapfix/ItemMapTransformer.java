package nf.fr.ephys.mapfix;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;

public class ItemMapTransformer implements IClassTransformer {
	@Override
	public byte[] transform(String className, String classNameCopy, byte[] bytes) {
		boolean obfuscated = className.equals("adh");

		if (!obfuscated && !className.equals("net.minecraft.item.ItemMap")) return bytes;

		String methodName = obfuscated ? "a" : "onUpdate";
		String signature = obfuscated ? "(Ladd;Lahb;Lsa;IZ)V" : "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;IZ)V";

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for (MethodNode method : classNode.methods) {
			if (method.name.equals(methodName) && method.desc.equals(signature)) {
				Iterator<AbstractInsnNode> iter = method.instructions.iterator();

				while (iter.hasNext()) {
					AbstractInsnNode node = iter.next();

					if (node.getOpcode() == Opcodes.ILOAD) {
						AbstractInsnNode ifeq = iter.next();

						method.instructions.remove(node);
						method.instructions.remove(ifeq);

						break;
					}
				}

				break;
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);

		return writer.toByteArray();
	}
}