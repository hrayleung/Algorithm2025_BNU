import java.util.Scanner;
import java.util.Random;

public class lab0103 {
    private static final double time = 5 * 365 * 24;
    private static final double arrival_min = 15.0;
    private static final double arrival_max = 45.0;

    private static final double service_min = 4.0;
    private static final double service_max = 44.0;

    private static final double wait_cost = 1000.0;

    private static final double expansion_cost = 13_500_000.0;

    private static final int count = 10000;

    public static void main(String[] args) {
        Random rand = new Random();
        double sumWaitingCost = 0;
        int countExceed = 0;

        for (int i = 0; i < count; i++) {
            double waitingCost = OneRun(rand);
            sumWaitingCost += waitingCost;
            if (waitingCost > expansion_cost) {
                countExceed++;
            }
        }

        double avgWaitingCost = sumWaitingCost / count;
        double probabilityExceed = countExceed / count;

        System.out.println("Average waiting cost is " + avgWaitingCost);
        System.out.println("Probability of Exceeding " + probabilityExceed);
    }

    private static double OneRun(Random rand) {
        double currentTime = 0.0;
        double lastDepartureTime = 0.0;
        double totalWaitingCost = 0.0;

        int shipCount = 0;

        double arrivalTime = 0.0;

        while (arrivalTime < time) {
            shipCount++;

            double serviceStartTime = Math.max(arrivalTime, lastDepartureTime);

            double waitingTime = serviceStartTime - arrivalTime;

            totalWaitingCost += waitingTime * wait_cost;

            double serviceTime = service_min + (service_max - serviceStartTime) * rand.nextDouble();

            double departureTime = serviceStartTime + serviceTime;

            lastDepartureTime = departureTime;

            double interArrivalTime = arrival_min + (arrival_max - arrivalTime) * rand.nextDouble();

            arrivalTime = arrivalTime + interArrivalTime;

        }
        System.out.println("Ship count " + shipCount);

        return totalWaitingCost;

    }
}
