package CurrencyExchange;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CurrencyExchange {
    private static final double INF = Double.MAX_VALUE;

    // Function to find arbitrage opportunities in currency exchange rates
    public void findArbitrage(double[][] rates, String[] currencies) {
        int N = rates.length;
        double[][] logRates = new double[N][N];

        // Convert exchange rates to logarithmic form
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                logRates[i][j] = -Math.log(rates[i][j]);
            }
        }


        // Arrays to store next currency in the arbitrage cycle and distances
        int[] next = new int[N];
        double[] dist = new double[N];
        Arrays.fill(dist, INF);
        boolean foundArbitrage = false;

        // Iterate over each currency as a starting point
        for (int startCurrency = 0; startCurrency < N; startCurrency++) {
            Arrays.fill(dist, INF);
            dist[startCurrency] = 0;
            // Bellman-Ford algorithm to find arbitrage opportunities
            for (int k = 0; k < N; k++) {
                boolean updated = false;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++)
                        if (dist[i] + logRates[i][j] < dist[j]) {
                            dist[j] = dist[i] + logRates[i][j];
                            next[j] = i;
                            updated = true;
                        }
                }
                if (!updated) break;


                if (k == N - 1) {
                    for (int i = 0; i < N; i++) {
                        if (dist[i] + logRates[i][next[i]] < dist[next[i]]) {
                            foundArbitrage = true;
                            double negativeWeightSum = dist[i] + logRates[i][next[i]];
                            int[] cycle = new int[N + 1];
                            Set<Integer> visited = new HashSet<>();

                            int p = i;
                            int m;
                            for (m = 0; m < N; m++) {
                                if (visited.contains(p)) {
                                    System.out.println("No arbitrage opportunity.");
                                    startCurrency = N;
                                    break;
                                }
                                visited.add(p);

                                cycle[m] = p;
                                p = next[p];

                                if (p == i) {
                                    cycle[m + 1] = cycle[0];
                                    // Check if the cycle has adjacent repeated currencies and has at least 3 currencies
                                    boolean validCycle = true;
                                    if (m >= 2 && cycle[0] != cycle[m]) {  // Ensure at least 3 currencies and no repeated adjacent currencies
                                        for (int n = 0; n < m; n++) {
                                            if (cycle[n] == cycle[n + 1]) {
                                                validCycle = false;
                                                break;
                                            }
                                        }
                                    } else {
                                        validCycle = false;  // Less than 3 currencies, or only 2 currencies with the same start and end
                                    }

                                    if (validCycle) {
                                        System.out.println("套利机会：");
                                        for (int n = 0; n <= m; n++) {
                                            System.out.printf("%d ", cycle[n] + 1);
                                        }
                                        System.out.printf("%d ", cycle[0] + 1);
                                        System.out.println();
                                        for (int n = 0; n <= m; n++) {
                                            System.out.printf(currencies[cycle[n]] + " to ");
                                        }
                                        System.out.printf(currencies[cycle[0]]);
                                        System.out.println();
                                        System.out.println("负权相加的和：" + negativeWeightSum);
                                        // Add logic to break out of the outer loop
                                        startCurrency = N; // This will make the outer loop terminate
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        // No arbitrage opportunity found
        if (!foundArbitrage) {
            System.out.println("No arbitrage opportunity.");
        }
    }
}
