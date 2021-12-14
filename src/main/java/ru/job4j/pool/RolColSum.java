package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int rowS = 0;
            int colS = 0;
            for (int j = 0; j < matrix.length; j++) {
                rowS = rowS + matrix[i][j];
                colS = colS + matrix[j][i];
            }
            sums[i] = new Sums(rowS, colS);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] array = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            array[i] = getTask(matrix, i).get();
        }
        return array;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int rowS = 0;
                    int colS = 0;
                    for (int i = 0; i < matrix.length; i++) {
                        rowS = rowS + matrix[index][i];
                        colS = colS + matrix[i][index];
                    }
                    return new Sums(rowS, colS);
                }
        );
    }
}
