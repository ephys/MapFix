package nf.fr.ephys.mapfix;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;

public class ItemMapTransformer implements IClassTransformer {
	public static final int ILOAD_OPCODE = 0x15;
//	public static final int IFEQ_OPCODE = 0x99;

	@Override
	public byte[] transform(String className, String classNameCopy, byte[] bytes) {
		boolean obfuscated = className.equals("adh");

		if (!obfuscated && !className.equals("net.minecraft.item.ItemMap")) return bytes;

		String methodName = obfuscated ? "func_77663_a" : "onUpdate";

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for (MethodNode method : classNode.methods) {
			if (method.name.equals(methodName)) {
				Iterator<AbstractInsnNode> iter = method.instructions.iterator();

				while (iter.hasNext()) {
					AbstractInsnNode node = iter.next();

					if (node.getOpcode() == ILOAD_OPCODE) {
						AbstractInsnNode ifeq = iter.next();

						method.instructions.remove(node);
						method.instructions.remove(ifeq);

						break;
					}
				}

				break;
			}
		}

		//ASM specific for cleaning up and returning the final bytes for JVM processing.
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);

		return writer.toByteArray();
	}
}