package org.example;

import java.util.Stack;

/**
 *  Name: Michael Atagamen
 *  Class Group: SD2A
 */
public class CA3_Question1
{
    private static Stack<Integer> drivewayStack = new Stack<>();


    private static Stack<Integer> streetStack = new Stack<>();

    public static void runSimulation()
    {
        // Example: Adding cars to the driveway
        addCarToDriveway(1);
        addCarToDriveway(2);
        addCarToDriveway(3);

        // Example: Retrieving cars from the driveway
        retrieveCarFromDriveway(2);
        retrieveCarFromDriveway(1);

        // Stop the simulation
        stopSimulation();
    }

    private static void addCarToDriveway(int carNumber)
    {
        drivewayStack.push(carNumber);
        System.out.println("Added car " + carNumber + " to the driveway. Driveway: " + drivewayStack);
    }

    private static void retrieveCarFromDriveway(int carNumber)
    {
        // Move cars from the driveway to the street until the requested car is retrieved
        while (!drivewayStack.isEmpty() && drivewayStack.peek() != carNumber)
        {
            int movedCar = drivewayStack.pop();
            streetStack.push(movedCar);
            System.out.println("Moved car " + movedCar + " to the street. Driveway: " + drivewayStack + ", Street: " + streetStack);
        }

        // Retrieve the requested car
        if (!drivewayStack.isEmpty() && drivewayStack.peek() == carNumber)
        {
            int retrievedCar = drivewayStack.pop();
            System.out.println("Retrieved car " + retrievedCar + " from the driveway. Driveway: " + drivewayStack);
        }
        else
        {
            System.out.println("Car " + carNumber + " not found in the driveway.");
        }
    }

    private static void stopSimulation()
    {
        // Stop the simulation (you can perform any cleanup operations here)
        System.out.println("Simulation stopped.");
    }

    public static void main(String[] args)
    {
        runSimulation();
    }
}
