package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Name: Michael Atagamen
 * Class Group: SD2A
 */
public class CA3_Question6 {
    // Class to represent a block of shares
    static class Block {
        int quantity;
        double price;

        public Block(int quantity, double price) {
            this.quantity = quantity;
            this.price = price;
        }
    }

    private static Queue<Block> sharesQueue = new LinkedList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String command = "";

        do {
            try {
                System.out.print("Enter command (buy, sell, quit): ");
                command = in.next();

                if (command.equalsIgnoreCase("buy")) {
                    int qty = getIntInput("Enter quantity: ");
                    double price = getDoubleInput("Enter price: ");
                    buy(qty, price);
                } else if (command.equalsIgnoreCase("sell")) {
                    int qty = getIntInput("Enter quantity: ");
                    sell(qty);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                in.nextLine(); // Consume invalid input to avoid an infinite loop
            }

        } while (!command.equalsIgnoreCase("quit"));

        in.close(); // Close the scanner when done
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            System.out.print(prompt);
            in.next(); // Consume invalid input
        }
        return in.nextInt();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!in.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid double.");
            System.out.print(prompt);
            in.next(); // Consume invalid input
        }
        return in.nextDouble();
    }

    private static void buy(int quantity, double price) {
        sharesQueue.offer(new Block(quantity, price));
        System.out.println("Bought " + quantity + " shares at $" + price + " each.");
    }

    private static void sell(int quantity) {
        double totalGain = 0;

        while (quantity > 0 && !sharesQueue.isEmpty()) {
            Block block = sharesQueue.poll();
            int qtyToSell = Math.min(quantity, block.quantity);

            double profit = (block.price - block.price) * qtyToSell;  // Corrected line
            totalGain += profit;

            quantity -= qtyToSell;
            block.quantity -= qtyToSell;

            if (block.quantity > 0) {
                sharesQueue.offer(block);
            }
        }

        System.out.println("Sold " + quantity + " shares for a total gain of $" + totalGain);
    }
}
