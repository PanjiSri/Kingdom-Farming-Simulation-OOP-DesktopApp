package lorem;

import plugin.DataPlugin;
import plugin.PluginLoader;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final Map<Integer, DataPlugin> plugins = new HashMap<>();
    private static final PluginLoader pluginLoader = new PluginLoader();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Default plugin for reading TXT files
        plugins.put(1, (path) -> System.out.println("Reading data from TXT file: " + path));

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            if (choice != 99 && plugins.containsKey(choice)) {
                System.out.print("Enter file path: ");
                String path = scanner.next();
                plugins.get(choice).readFromFile(path);
            } else if (choice == 99) {
                System.out.print("Enter path to plugin jar: ");
                String jarPath = scanner.next();
                System.out.print("Enter plugin class name: ");
                String className = scanner.next();
                try {
                    loadPlugin(jarPath, className);
                } catch (Exception e) {
                    System.out.println("Failed to load plugin: " + e.getMessage());
                }
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nMenu");
        plugins.forEach((option, plugin) -> {
            System.out.println(option + ". Read file from " + (option == 1 ? ".txt" : "loaded plugin"));
        });
        System.out.println("99. Load plugin");
        System.out.println("0. Exit");
    }

    private static void loadPlugin(String jarPath, String className) throws Exception {
        DataPlugin plugin = pluginLoader.loadPlugin(jarPath, className);
        plugins.put(2, plugin);  // Assuming 2 is the dynamic plugin option
        System.out.println("Plugin loaded successfully and added as option 2.");
    }
}
