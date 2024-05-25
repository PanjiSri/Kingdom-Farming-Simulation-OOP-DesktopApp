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
            results.addAll(parseElement(rootNode));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error reading XML file: " + fullPath);
        }
        return results;
    }

    private ArrayList<String> parseElement(Element element) {
        ArrayList<String> list = new ArrayList<>();
        List<Element> children = element.getChildren();
        if (!element.getTextTrim().isEmpty()) {
            list.add(element.getTextTrim());
        }
        // Handling specific structured elements
        for (Element child : children) {
            switch (child.getName()) {
                case "Item":
                    list.add(child.getAttributeValue("name") + " " + child.getAttributeValue("quantity"));
                    break;
                case "Card":
                    list.add(buildCardDescription(child));
                    break;
                case "ActiveDeck":
                case "LandCards":
                case "ShopItems":
                    list.addAll(parseElement(child)); 
                    break;
                default:
                    list.addAll(parseElement(child)); 
                    break;
            }
        }
        return list;
    }

    private String buildCardDescription(Element cardElement) {
        return cardElement.getAttributeValue("location") + " " +
               cardElement.getAttributeValue("animal") + " " +
               cardElement.getAttributeValue("age", "") + " " +
               cardElement.getAttributeValue("items", "").replaceAll("\\s+", " ");
    }
}

