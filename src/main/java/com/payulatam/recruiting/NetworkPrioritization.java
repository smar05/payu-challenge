package com.payulatam.recruiting;

import java.util.Arrays;

import com.payulatam.recruiting.services.OrderNetworkService;

public class NetworkPrioritization {

    private final OrderNetworkService orderNetworkService;

    public NetworkPrioritization() {
        this.orderNetworkService = new OrderNetworkService();
    }

    public enum Criteria {
        RESPONSE_TIME, COST;
    }

    public int[] prioritizeNetwork(final int[] responseTimes, final int[] cost, final Criteria priority) {
        final Integer[] indices = switch (priority) {
            case RESPONSE_TIME -> orderNetworkService.orderNetwork(responseTimes, cost);
            case COST -> orderNetworkService.orderNetwork(cost, responseTimes);
        };

        return Arrays.stream(indices).mapToInt(Integer::intValue).toArray();
    }

    // TODO: Borrar main
    public static void main(String[] args) {
        final NetworkPrioritization np = new NetworkPrioritization();
        final int[] responseTimes = { 100, 200, 150, 120 };
        final int[] costs = { 10, 5, 8, 12 };

        final int[] prioritizedByResponse = np.prioritizeNetwork(responseTimes, costs, Criteria.RESPONSE_TIME);
        System.out.println("RESPONSE_TIME: " + Arrays.toString(prioritizedByResponse));

        final int[] prioritizedByCost = np.prioritizeNetwork(responseTimes, costs, Criteria.COST);
        System.out.println("COST: " + Arrays.toString(prioritizedByCost));
    }
}
