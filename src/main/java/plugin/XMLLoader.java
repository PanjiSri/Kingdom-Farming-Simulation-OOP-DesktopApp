package plugin;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XMLLoader implements DataPlugin {

    @Override
    public ArrayList<String> readFromFile(String folderName, String fileName) {
        ArrayList<String> results = new ArrayList<>();
        String fullPath = System.getProperty("user.dir") + "/" + folderName + "/" + fileName;
        SAXBuilder builder = new SAXBuilder();
        try {
            Document document = builder.build(Paths.get(fullPath).toFile());
            Element rootNode = document.getRootElement();
            parseElement(rootNode, results);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error reading XML file: " + fullPath);
        }
        return results;
    }

    private void parseElement(Element element, List<String> list) {
        switch (element.getName()) {
            case "Gold":
            case "DeckCount":
            case "ActiveDeckCount":
            case "CurrentTurn":
            case "NumberOfItemsInShop":
            case "LandCardCount":
            case "LandCardsCount":
                list.add(element.getTextTrim());
                break;
            case "Item":
                list.add(element.getAttributeValue("name") + " " + element.getAttributeValue("quantity"));
                break;
            case "Card":
                // Concatenate card details
                StringBuilder cardDetails = new StringBuilder();
                cardDetails.append(element.getAttributeValue("location")).append(" ")
                           .append(element.getAttributeValue("animal")).append(" ");
                if (element.getAttributeValue("age") != null && !element.getAttributeValue("age").isEmpty()) {
                    cardDetails.append(element.getAttributeValue("age")).append(" ");
                }
                if (element.getAttributeValue("items") != null && !element.getAttributeValue("items").isEmpty()) {
                    cardDetails.append(element.getAttributeValue("items"));
                }
                list.add(cardDetails.toString().trim());
                break;
            default:
                // Recursively parse all child elements
                for (Element child : element.getChildren()) {
                    parseElement(child, list);
                }
                break;
        }
    }
}