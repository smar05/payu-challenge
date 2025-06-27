package com.payulatam.recruiting;

import java.util.Arrays;

public class NetworkPrioritization {

    public enum Criteria {
        RESPONSE_TIME, COST;
    }

    public int[] prioritizeNetwork(final int[] responseTimes, final int[] cost, final Criteria priority) {
        final Integer[] indices = switch (priority) {
            case RESPONSE_TIME -> orderNetwork(responseTimes, cost);
            case COST -> orderNetwork(cost, responseTimes);
        };

        return Arrays.stream(indices).mapToInt(Integer::intValue).toArray();
    }

    private Integer[] orderNetwork(final int[] firstArray, final int[] secondArray) {
        // Validacion de la longitud de los arrays
        if (firstArray.length != secondArray.length) {
            throw new IllegalArgumentException("The input arrays lengths are not equal.");
        }

        // Indices de las redes iniciales
        final int n = firstArray.length;
        final Integer[] indices = new Integer[n];
        Arrays.setAll(indices, i -> i);

        Arrays.sort(indices, (a, b) -> {
            // Si los valores son distintos, se toma el menor valor del mismo array
            if (firstArray[a] != firstArray[b]) {
                return Integer.compare(firstArray[a], firstArray[b]);
            }

            // Si los valores son iguales, se desempata en base al otro array
            return Integer.compare(secondArray[a], secondArray[b]);
        });

        return indices;
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
