package plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TXTSaver {

    public List<String> repairFormatGameState(List<String> unformatted_list){

        ArrayList<String> formatted_list  = new ArrayList<>();
        
        if (!unformatted_list.isEmpty()) {
            formatted_list.add(unformatted_list.get(0));  // First item as a single line
        }

        if (unformatted_list.size() > 1) {
            formatted_list.add(unformatted_list.get(1));  // Second item as a single line
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

    public void saveFormattedData(String directoryName, String fileName, List<String> data) {
        Path directoryPath = Paths.get(directoryName);
        Path filePath = directoryPath.resolve(fileName);

        try {
            Files.createDirectories(directoryPath); // Ensure directory exists

            StringBuilder contentBuilder = new StringBuilder();
            for (String line : data) {
                contentBuilder.append(line).append("\n");
            }

            // Ensure the file is truncated before writing new content
            Files.write(filePath, contentBuilder.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Data successfully saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
}