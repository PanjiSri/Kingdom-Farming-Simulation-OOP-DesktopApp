package plugin.implementasi;

import plugin.DataPlugin;

public class XMLPlugin implements DataPlugin {
    @Override
    public void readFromFile(String path) {
        System.out.println("Reading data from XML file: " + path);
        // Add actual XML reading logic here
    }
}
