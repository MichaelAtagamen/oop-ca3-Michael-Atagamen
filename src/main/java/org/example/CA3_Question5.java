package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *  Name: Michael Atagamen
 *  Class Group: SD2A
 */
public class CA3_Question5
{
    private static Queue<String> takeoffQueue = new LinkedList<>();
    private static Queue<String> landingQueue = new LinkedList<>();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.print("Enter command (takeoff, land, next, quit): ");
            command = scanner.next();


            switch (command.toLowerCase()) {
                case "takeoff":
                    String takeoffFlight = scanner.next();
                    takeoff(takeoffFlight);
                    break;
                case "land":
                    String landFlight = scanner.next();
                    land(landFlight);
                    break;
                case "next":
                    next();
                    break;
                case "quit":
                    System.out.println("Quitting the simulation.");
                    break;
                default:
                    System.out.println("Invalid command. Please enter takeoff, land, next, or quit.");
            }

        } while (!command.toLowerCase().equals("quit"));
    }

    private static void takeoff(String flight)
    {
        takeoffQueue.offer(flight);
        System.out.println("Flight " + flight + " is queued for takeoff.");
    }

    private static void land(String flight)
    {
        landingQueue.offer(flight);
        System.out.println("Flight " + flight + " is queued for landing.");
    }

    private static void next()
    {
        if (!landingQueue.isEmpty()) {
            String landedFlight = landingQueue.poll();
            System.out.println("Landing: Flight " + landedFlight);
        } else if (!takeoffQueue.isEmpty()) {
            String takenOffFlight = takeoffQueue.poll();
            System.out.println("Takeoff: Flight " + takenOffFlight);
        } else {
            System.out.println("No flights in the queues.");
        }
    }
}
