package plugin;

import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;

public class PluginLoader {
    public DataPlugin loadPlugin(String jarPath, String className) throws Exception {
        try {
            File jarFile = new File(jarPath);
            URL jarURL = jarFile.toURI().toURL();
            System.out.println("jarURL: " + jarURL);
            try (URLClassLoader loader = new URLClassLoader(new URL[]{jarURL})) {
                Class<?> clazz = Class.forName(className, true, loader);
                return (DataPlugin) clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            System.err.println("Error loading plugin: " + e.toString());
            throw e;
        }
    }
}
