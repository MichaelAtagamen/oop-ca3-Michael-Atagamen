package org.example;

import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Michael Atagamen
 *  Class Group: SD2A
 */
public class CA3_Question2
{
    /*
        Starter function to create the 2D array and populate it with 0
     */
    public static int[][] floodFillStart() {
        Scanner kb = new Scanner(System.in);
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                arr[x][y] = 0;
            }
        }
        return arr;
    }

    /*
        Helper function to display the image
     */
    public static void display(int[][] arr) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }

    /*
        Function to perform flood fill algorithm
     */
    private static void fill(int r, int c, int[][] arr) {
        Stack<Pair> stack = new Stack<>();
        int fillNumber = 1;

        // Push the starting coordinates onto the stack
        stack.push(new Pair(r, c));

        while (!stack.isEmpty()) {
            // Pop off the (row, column) pair from the top of the stack
            Pair currentPair = stack.pop();
            int row = currentPair.row;
            int col = currentPair.col;

            // If it has not yet been filled, fill the corresponding cell location
            if (arr[row][col] == 0) {
                arr[row][col] = fillNumber++;
            }

            // Push the coordinates of any unfilled neighbors
            pushUnfilledNeighbors(row - 1, col, stack, arr);
            pushUnfilledNeighbors(row + 1, col, stack, arr);
            pushUnfilledNeighbors(row, col - 1, stack, arr);
            pushUnfilledNeighbors(row, col + 1, stack, arr);
        }
    }

    /*
        Helper function to push coordinates of unfilled neighbors onto the stack
     */
    private static void pushUnfilledNeighbors(int row, int col, Stack<Pair> stack, int[][] arr) {
        if (row >= 0 && row < 10 && col >= 0 && col < 10 && arr[row][col] == 0) {
            stack.push(new Pair(row, col));
        }
    }

    /*
        Pair class to store row and column coordinates
     */
    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /*
        Function to start the flood fill simulation
     */
    public static void start() {
        int[][] arr = floodFillStart();

        // Prompt for the starting row and column
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter starting row: ");
        int startRow = scanner.nextInt();
        System.out.print("Enter starting column: ");
        int startCol = scanner.nextInt();

        // Perform the flood fill algorithm
        fill(startRow, startCol, arr);

        // Display the final 2D array
        display(arr);
    }

    public static void main(String[] args) {
        start();
    }
}
