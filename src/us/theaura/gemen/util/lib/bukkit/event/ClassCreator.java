package us.theaura.gemen.util.lib.bukkit.event;

import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import sun.misc.SharedSecrets;

/**
 * Used to dynamically define classes.
 * 
 * @author rbrick
 */
public class ClassCreator extends URLClassLoader {

    private Map<String, Class<?>> classCache = new HashMap<>();

    public ClassCreator(ClassLoader parent) {
        super(SharedSecrets.getJavaNetAccess().getURLClassPath((URLClassLoader) parent).getURLs(), parent);
    }

    public Class<?> createClass(String name, byte[] data) {
        if (classCache.containsKey(name)) {
            return classCache.get(name);
        }
        
        Class<?> x = defineClass(name, data, 0, data.length);
        classCache.put(name, x);
        return x;
    }
    
}