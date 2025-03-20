import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class lab0103_a {
    static final double SIMULATION_TIME = 5 * 365 * 24;

    static final double ARRIVAL_MIN = 15.0;
    static final double ARRIVAL_MAX = 45.0;
    static final double SERVICE_MIN = 4.0;
    static final double SERVICE_MAX = 44.0;
    static final double WAIT_COST_PER_HOUR = 1000.0;
    static final double EXPANSION_COST = 13_500_000.0;
    static final int SIMULATION_COUNT = 10000;

    public static void main(String[] args) {
        double sumWaitingCost = IntStream.range(0, SIMULATION_COUNT)
                .parallel()
                .mapToDouble(i -> simulateOneRun())
                .sum();

        long countExceed = IntStream.range(0, SIMULATION_COUNT)
                .parallel()
                .filter(i -> simulateOneRun() > EXPANSION_COST)
                .count();

        double avgWaitingCost = sumWaitingCost / SIMULATION_COUNT;
        double probabilityExceed = (double) countExceed / SIMULATION_COUNT;

        System.out.println("基于模拟的码头运营结果：");
        System.out.printf("在未来5年内，平均等待成本为：%,.2f 元%n", avgWaitingCost);
        System.out.printf("等待成本超过扩建费用 (%,.0f 元) 的概率为：%.2f%%%n", EXPANSION_COST, probabilityExceed * 100);
        System.out.println();

        if (avgWaitingCost > EXPANSION_COST) {
            System.out.println("建议——扩建码头：预计未来5年的等待成本超过扩建费用。");
        } else {
            System.out.println("建议——不扩建码头：预计未来5年的等待成本低于扩建费用。");
        }

        if (probabilityExceed > 0.5) {
            System.out.println("另外，超过50%的模拟结果显示等待成本超过扩建费用，可以考虑扩建。");
        } else {
            System.out.println("另外，超过50%的模拟结果显示等待成本低于扩建费用，不建议扩建。");
        }
    }

    static double simulateOneRun() {
        double lastDepartureTime = 0.0;
        double totalWaitingCost = 0.0;
        double arrivalTime = 0.0;
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        while (arrivalTime < SIMULATION_TIME) {
            double serviceStartTime = Math.max(arrivalTime, lastDepartureTime);
            double waitingTime = serviceStartTime - arrivalTime;
            totalWaitingCost += waitingTime * WAIT_COST_PER_HOUR;
            double serviceTime = SERVICE_MIN + (SERVICE_MAX - SERVICE_MIN) * rand.nextDouble();
            double departureTime = serviceStartTime + serviceTime;
            lastDepartureTime = departureTime;
            double interArrivalTime = ARRIVAL_MIN + (ARRIVAL_MAX - ARRIVAL_MIN) * rand.nextDouble();
            arrivalTime += interArrivalTime;
        }
        return totalWaitingCost;
    }
}

