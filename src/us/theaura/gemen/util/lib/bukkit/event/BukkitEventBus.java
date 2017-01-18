package us.theaura.gemen.util.lib.bukkit.event;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Complex event bus using ASM for making events simpler and more dynamic.
 * 
 * @author rbrick
 */
public class BukkitEventBus implements Opcodes {

    private Plugin plugin;
    private ClassCreator classCreator;
    private Class<? extends Annotation> methodIdentifier = Subscriber.class;

    public BukkitEventBus(Plugin plugin) {
        this.plugin = plugin;
        this.classCreator = new ClassCreator(getClass().getClassLoader());
    }
    
    public void register(Object object) {
        Class<?> clazz = makeClass(object); // Make the new listener class
        try {
            // create a new listener; currently only allows zero arg constructors
            Listener listenerInstance = (Listener) clazz.newInstance(); 
            Bukkit.getPluginManager().registerEvents(listenerInstance, plugin); // finally register the event with bukkit
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private Class<?> makeClass(Object object) {
        // the class we will copy from
        ClassNode node = new ClassNode();
        // The class that will have methods copied to
        ClassNode newClass = createClassNode(object.getClass().getCanonicalName());

        try {
            // get the bytes of the class
            byte[] clazz = classToBytes(object.getClass());

            ClassReader reader = new ClassReader(clazz);
            reader.accept(node, 0);

            for (Object mn : node.methods) {
                MethodNode methodNode = (MethodNode) mn;
                // If it is the constructor method (<init>) or class init methods, just add to the new class
                if (methodNode.name.equals("<init>") || methodNode.name.equals("<clinit>")) {
                    newClass.methods.add(methodNode);
                    continue;
                }
                // Attempt to find the java.reflect Method in the class
                Method method = findFromName(object.getClass(), methodNode.name);
                if (method != null) {
                    method.setAccessible(true); // ensure we can access it

                    // Check annotations for the identifying annotation
                    for (Annotation annotation : method.getAnnotations()) {
                        if (annotation.annotationType() == methodIdentifier) {
                            // Before we copy the method, add Bukkit's EventHandler annotation to the method
                            AnnotationVisitor av = methodNode.visitAnnotation("Lorg/bukkit/event/EventHandler;", true);
                            av.visitEnd();
                        }
                    }
                    // now copy the method over
                    newClass.methods.add(methodNode);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        newClass.accept(writer); // inform the writer of the contents of the node

        return classCreator.createClass(newClass.name.replace('/', '.'), writer.toByteArray()); // create the class!!!!
    }

    private byte[] classToBytes(Class<?> clazz) throws IOException {
        InputStream inputStream = plugin.getClass().getClassLoader().getResourceAsStream(clazz.getCanonicalName().replace('.', '/') + ".class");
        byte[] classData = new byte[inputStream.available()];
        inputStream.read(classData);
        return classData;
    }

    private ClassNode createClassNode(String baseName) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        String fixedBaseName = baseName.replace('.', '/');

        writer.visit(Opcodes.V1_8, ACC_PUBLIC, fixedBaseName + "$$Generated$$Listener", null, "java/lang/Object", new String[]{"org/bukkit/event/Listener"});
        writer.visitEnd();

        ClassReader reader = new ClassReader(writer.toByteArray());
        ClassNode node = new ClassNode(ASM5);
        reader.accept(node, 0);

        return node;
    }

    private Method findFromName(Class<?> clazz, String name) {
        Method found = null;
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(name)) {
                found = method;
            }
        }
        return found;
    }
    
}