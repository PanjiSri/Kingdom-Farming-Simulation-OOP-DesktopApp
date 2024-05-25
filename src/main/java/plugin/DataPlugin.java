package plugin;

import java.util.ArrayList;

public interface DataPlugin {
    public ArrayList<String> readFromFile(String folderName, String fileName);
}
