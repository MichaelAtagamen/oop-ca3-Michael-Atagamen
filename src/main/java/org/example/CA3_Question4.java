package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Name: Michael.Atagamen
 * Class Group: SD2A
 */
public class CA3_Question4 {

    /*
        filename: name of the file to test.
     */
    public static boolean validate(String filename) throws FileNotFoundException {
        Stack<String> tagStack = new Stack<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] tags = line.split("\\s+");

            for (String tag : tags) {
                if (tag.startsWith("</")) {
                    // Closing tag
                    String openingTag = tagStack.pop();
                    if (!openingTag.equals(tag.substring(1))) {
                        return false; // Invalid nesting
                    }
                } else if (tag.startsWith("<")) {
                    // Opening tag
                    tagStack.push(tag);
                }
            }
        }

        return tagStack.isEmpty(); // All tags should be properly closed
    }

    /*
        This function tests the files in the files array to see if
         they are valid.
         tags_valid.txt should return true;
         tags_invalid.txt should output as invalid;
         Stack.pop used to find closing tags
     */
    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};

        for (String fName : files) {
            System.out.print(fName + ": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
                // if this is true it should print valid
            } else {
                // if it is not true you print false
                System.out.println("This file is invalid");
            }
        }
    }
}
