import java.util.Scanner;
import java.util.Random;

public class lab0103 {
    // Constants for simulation parameters
    private static final double time = 5 * 365 * 24;          // Total simulation time in hours (5 years)
    private static final double arrival_min = 15.0;           // Minimum inter-arrival time between ships (hours)
    private static final double arrival_max = 45.0;           // Maximum inter-arrival time between ships (hours)
    private static final double service_min = 4.0;            // Minimum service time for a ship (hours)
    private static final double service_max = 44.0;           // Maximum service time for a ship (hours)
    private static final double wait_cost = 1000.0;           // Cost per hour of waiting time ($)
    private static final double expansion_cost = 13_500_000.0;// Expansion project cost threshold ($)
    private static final int count = 10000;                   // Number of simulation runs
    public static void main(String[] args) {
        Random rand = new Random();
        double sumWaitingCost = 0;            // Total waiting cost across all simulations
        int countExceed = 0;                  // Count of how many simulations exceed expansion cost

        // Run simulations 'count' times
        for (int i = 0; i < count; i++) {
            double waitingCost = OneRun(rand);
            sumWaitingCost += waitingCost;
            // Check if waiting cost exceeds expansion threshold
            if (waitingCost > expansion_cost) {
                countExceed++;
            }
        }

        // Calculate final results
        double avgWaitingCost = sumWaitingCost / count;             // Average waiting cost per simulation
        double probabilityExceed = (double)countExceed / count;     // Probability of exceeding expansion cost

        // Output results
        System.out.println("Average waiting cost is " + avgWaitingCost);
        System.out.println("Probability of Exceeding " + probabilityExceed);
    }

    // Performs one complete simulation run and returns total waiting cost
    private static double OneRun(Random rand) {
        double currentTime = 0.0;                // Not used in current implementation
        double lastDepartureTime = 0.0;          // Track when last ship left port
        double totalWaitingCost = 0.0;           // Accumulated waiting cost for this run
        int shipCount = 0;                       // Count of ships processed
        double arrivalTime = 0.0;                // Timing of next ship arrival

        // Simulation loop - generate ships until simulation period ends
        while (arrivalTime < time) {
            shipCount++;  // Increment ship count for each arrival

            // Determine service start time (max of arrival time and last departure)
            double serviceStartTime = Math.max(arrivalTime, lastDepartureTime);

            // Calculate ship's waiting time before service
            double waitingTime = serviceStartTime - arrivalTime;
            totalWaitingCost += waitingTime * wait_cost; // Add waiting cost to total

            // Generate random service time for current ship
            // Correction: Changed service_max - serviceStartTime to service_max - service_min
            double serviceTime = service_min + (service_max - service_min) * rand.nextDouble();

            // Calculate departure time
            double departureTime = serviceStartTime + serviceTime;
            lastDepartureTime = departureTime;

            // Generate inter-arrival time for next ship
            // Correction: Changed arrival_max - arrivalTime to arrival_max - arrival_min
            double interArrivalTime = arrival_min + (arrival_max - arrival_min) * rand.nextDouble();

            // Schedule next arrival time
            arrivalTime += interArrivalTime;
        }

        // Diagnostic output: Number of ships processed in this run
        System.out.println("Ship count " + shipCount);

        return totalWaitingCost;
    }
}
