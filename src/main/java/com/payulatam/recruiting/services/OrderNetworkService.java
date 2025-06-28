package com.payulatam.recruiting.services;

import java.util.Arrays;

public class OrderNetworkService {
    public Integer[] orderNetwork(final int[] firstArray, final int[] secondArray) {
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
}
