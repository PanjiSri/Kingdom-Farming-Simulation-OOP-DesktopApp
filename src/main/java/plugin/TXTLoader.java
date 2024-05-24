package plugin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TXTLoader{

    private final String basePath;

    public TXTLoader(String folderName) {
        this.basePath = System.getProperty("user.dir") + "/" + folderName + "/";
    }
    
    public List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get(basePath + fileName);
            lines = Files.readAllLines(path);
        } catch (Exception e) {
            System.err.println("Error reading the file: " + fileName);
            e.printStackTrace();
        }
        return lines;
    }
}