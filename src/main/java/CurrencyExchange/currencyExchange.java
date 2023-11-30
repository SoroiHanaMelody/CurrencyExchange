package CurrencyExchange;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class currencyExchange {
    private static final int INF = 1000000;

    // Function to find arbitrage opportunities in currency exchange rates
    private static void findArbitrage(double[][] rates) {
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
        dist[0] = 0;

        // Bellman-Ford algorithm to find arbitrage opportunities
        for (int k = 0; k < N; k++) {
            boolean updated = false;
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++)
                    if (dist[i] + logRates[i][j] < dist[j]) {
                        dist[j] = dist[i] + logRates[i][j];
                        next[j] = i;
                        updated = true;
                    }
            }
            if (!updated) break;

            // Check for arbitrage opportunity after N iterations
            if (k == N-1) {
                for (int i = 0; i < N; i++) {
                    if (dist[i] + logRates[i][next[i]] < dist[next[i]]) {
                        int[] cycle = new int[N+1];
                        for (int j = 0; j < N; j++)
                            i = next[i];
                        Set<Integer> visited = new HashSet<>();
                        int p = i;
                        for (int m = 0; m < N; m++) {
                            if (visited.contains(p)) {
                                System.out.println("Error: Unable to find the arbitrage opportunity.");
                                return;
                            }
                            visited.add(p);

                            cycle[m] = p;
                            p = next[p];

                            if (p == cycle[0]) {
                                cycle[m+1] = cycle[0];
                                System.out.println("Arbitrage opportunity:");
                                for (int n = m; n >= 0; n--)
                                    System.out.printf("%d ", cycle[n]+1);
                                System.out.println();
                                return;
                            }
                        }
                    }
                }
            }


        }

        // No arbitrage opportunity found
        System.out.println("No arbitrage opportunity.");
    }



    public static void main(String[] args) {
        double[][] ratesExample = {
                {1, 0.741, 0.657, 1.061, 1.005},
                {1.349, 1, 0.888, 1.433, 1.366},
                {1.521, 1.126, 1, 1.614, 1.538},
                {0.942, 0.698, 0.619, 1, 0.953},
                {0.995, 0.732, 0.650, 1.049, 1}
        };
        double[][] ratesExample2 = {
                {1, 0.9, 1.2, 0.8, 1.1},
                {1.1, 1, 1.3, 0.7, 0.9},
                {0.8, 1.2, 1, 1.1, 0.9},
                {1.3, 0.7, 0.9, 1, 1.2},
                {0.9, 1.1, 1.4, 0.8, 1}
        };
        double[][] ratesExample3 = {
                {1, 0.9, 1.2, 0.8},
                {1.1, 1, 1.3, 0.7},
                {0.8, 1.2, 1, 1.1},
                {1.3, 0.7, 0.9, 1}
        };

        findArbitrage(ratesExample);
        findArbitrage(ratesExample2);
        findArbitrage(ratesExample3);
    }
}
