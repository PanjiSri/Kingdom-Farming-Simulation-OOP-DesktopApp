package plugin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<String> tokenizeLines(List<String> lines) {
        List<String> tokens = new ArrayList<>();
        for (String line : lines) {
            String[] lineTokens = line.split("\\s+");
            tokens.addAll(Arrays.asList(lineTokens));
        }
        return tokens;
    }

    public List<String> repairFormat(List<String> unformatted_list){

        ArrayList<String> formatted_list  = new ArrayList<>();
        
        if (!unformatted_list.isEmpty()) {
            formatted_list.add(unformatted_list.get(0)); 
        }

        if (unformatted_list.size() > 1) {
            formatted_list.add(unformatted_list.get(1));  
        }

        for (int i = 2; i < unformatted_list.size(); i += 2) {
            String combinedLine = unformatted_list.get(i);
            if (i + 1 < unformatted_list.size()) {
                combinedLine += " " + unformatted_list.get(i + 1);
            }
           formatted_list.add(combinedLine);
        }
        
        return formatted_list;
    }
}