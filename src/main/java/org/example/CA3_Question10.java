package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


/**
 * Name: Michael.Atagamen
 * Class Group: SD2A
 */
public class CA3_Question10 {


    public static void main(String[] args) {
        try {
            // Read input from file
            Scanner scanner = new Scanner(new File("city_distances.txt"));

            // Map to store direct connections between cities
            Map<String, TreeSet<DistanceTo>> cityConnections = new HashMap<>();

            // Priority queue for the algorithm
            PriorityQueue<DistanceTo> priorityQueue = new PriorityQueue<>();

            // Map to store the shortest known distances
            Map<String, Integer> shortestKnownDistance = new HashMap<>();

            // Read the starting point
            String startingPoint = scanner.next();

            // Process input and populate cityConnections
            while (scanner.hasNext()) {
                String city1 = scanner.next();
                String city2 = scanner.next();
                int distance = scanner.nextInt();

                // Add direct connection from city1 to city2
                cityConnections.computeIfAbsent(city1, k -> new TreeSet<>()).add(new DistanceTo(city2, distance));
                // Add direct connection from city2 to city1
                cityConnections.computeIfAbsent(city2, k -> new TreeSet<>()).add(new DistanceTo(city1, distance));
            }

            // Add starting point to the priority queue
            priorityQueue.add(new DistanceTo(startingPoint, 0));

            // Process the priority queue
            while (!priorityQueue.isEmpty()) {
                DistanceTo current = priorityQueue.poll();
                String currentCity = current.getTarget();

                if (!shortestKnownDistance.containsKey(currentCity)) {
                    int d = current.getDistance();
                    shortestKnownDistance.put(currentCity, d);

                    // Explore direct connections from the current city
                    TreeSet<DistanceTo> connections = cityConnections.getOrDefault(currentCity, new TreeSet<>());
                    for (DistanceTo connection : connections) {
                        String nextCity = connection.getTarget();
                        int distanceToNext = connection.getDistance();
                        priorityQueue.add(new DistanceTo(nextCity, d + distanceToNext));
                    }
                }
            }

            // Print the shortest distances
            System.out.println("Shortest distances from " + startingPoint + ":");
            for (String city : shortestKnownDistance.keySet()) {
                int distance = shortestKnownDistance.get(city);
                System.out.println(city + ": " + distance);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class DistanceTo implements Comparable<DistanceTo> {
        private String target;
        private int distance;

        public DistanceTo(String city, int dist) {
            target = city;
            distance = dist;
        }

        public String getTarget() {
            return target;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(DistanceTo other) {
            return Integer.compare(distance, other.distance);
        }
    }
}
