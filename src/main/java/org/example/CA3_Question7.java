package org.example;

import java.util.*;

/**
 * Name: Michael Atagamen
 * Class Group: SD2A
 */
public class CA3_Question7 {
    // Class to represent a block of shares
    static class Block {
        String company;
        int quantity;
        double price;

        public Block(int quantity, double price) {
            this.quantity = quantity;
            this.price = price;
            this.company = company;
        }
    }

    // Map to manage separate queues for each stock symbol

    private static Map<String, Queue<Block>> sharesMap = new HashMap<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String command = "";




        do {
            try {
                System.out.print("Enter Company Name: ");
                command = in.next();

                System.out.print("Enter command (buy, sell, quit): ");
                command = in.next();

                if (command.equalsIgnoreCase("buy")) {
                    String symbol = in.next();
                    int qty = getIntInput("Enter quantity: ");
                    double price = getDoubleInput("Enter price: ");
                    buy(symbol, qty, price);
                } else if (command.equalsIgnoreCase("sell")) {
                    String symbol = in.next();
                    int qty = getIntInput("Enter quantity: ");
                    sell(symbol, qty);
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

    private static void buy(String symbol, int quantity, double price) {
        sharesMap.putIfAbsent(symbol, new LinkedList<>());
        sharesMap.get(symbol).offer(new Block(quantity, price));

        System.out.println("Bought " + quantity + " shares of " + symbol + " at $" + price + " each.");
    }

    private static void sell(String symbol, int quantity) {
        double totalGain = 0;

        Queue<Block> symbolQueue = sharesMap.get(symbol);

        if (symbolQueue != null) {
            while (quantity > 0 && !symbolQueue.isEmpty()) {
                Block block = symbolQueue.poll();
                int qtyToSell = Math.min(quantity, block.quantity);

                double profit = (block.price - block.price) * qtyToSell;
                totalGain += profit;

                quantity -= qtyToSell;
                block.quantity -= qtyToSell;

                if (block.quantity > 0) {
                    symbolQueue.offer(block);
                }
            }

            System.out.println("Sold " + quantity + " shares of " + symbol + " for a total gain of $" + totalGain);
        } else {
            System.out.println("No shares found for " + symbol);
        }
    }
}
