package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Name: Michael.Atagamen
 * Class Group: SD2A
 */
public class CA3_Question3 {
    public static void readFile(String fileName) throws FileNotFoundException {
        // Create a TreeMap to store identifiers and their occurrences with line numbers
        TreeMap<String, StringBuilder> identifierMap = new TreeMap<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("[^A-Za-z0-9_]+");

                while (lineScanner.hasNext()) {
                    String identifier = lineScanner.next();
                    // Update the TreeMap with the identifier and line number
                    identifierMap.computeIfAbsent(identifier, k -> new StringBuilder()).append(lineNumber).append(", ");
                }
            }
        }

        // Print the result
        for (String identifier : identifierMap.keySet()) {
            System.out.println(identifier + ": " + identifierMap.get(identifier));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile("src/main/java/org/example/CA3_Question1.java");
    }
}
