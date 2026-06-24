class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;   // number of possible values

        if (n == 1) {
            return m;
        }

        if (m == 1) {
            return 0;
        }

        int size = 2 * m;

        // Initial state for length 2
        long[] state = new long[size];

        for (int i = 0; i < m; i++) {
            state[i] = i;              // up[i]
            state[m + i] = m - 1 - i;  // down[i]
        }

        if (n == 2) {
            long ans = 0;
            for (long val : state) {
                ans = (ans + val) % MOD;
            }
            return (int) ans;
        }

        // Build transition matrix
        long[][] matrix = new long[size][size];

        for (int curr = 0; curr < m; curr++) {

            // newUp[curr] = sum of oldDown[prev] where prev < curr
            for (int prev = 0; prev < curr; prev++) {
                matrix[curr][m + prev] = 1;
            }

            // newDown[curr] = sum of oldUp[prev] where prev > curr
            for (int prev = curr + 1; prev < m; prev++) {
                matrix[m + curr][prev] = 1;
            }
        }

        // We already have length 2.
        // Need to reach length n, so apply transition n - 2 times.
        long[][] powered = matrixPower(matrix, n - 2);

        long[] finalState = multiplyMatrixVector(powered, state);

        long ans = 0;
        for (long val : finalState) {
            ans = (ans + val) % MOD;
        }

        return (int) ans;
    }

    private long[][] matrixPower(long[][] matrix, long power) {
        int size = matrix.length;

        long[][] result = new long[size][size];

        // Identity matrix
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        while (power > 0) {
            if ((power & 1) == 1) {
                result = multiplyMatrix(result, matrix);
            }

            matrix = multiplyMatrix(matrix, matrix);
            power >>= 1;
        }

        return result;
    }

    private long[][] multiplyMatrix(long[][] a, long[][] b) {
        int size = a.length;
        long[][] res = new long[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (a[i][k] == 0) continue;

                for (int j = 0; j < size; j++) {
                    if (b[k][j] == 0) continue;

                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return res;
    }

    private long[] multiplyMatrixVector(long[][] matrix, long[] vector) {
        int size = vector.length;
        long[] res = new long[size];

        for (int i = 0; i < size; i++) {
            long sum = 0;

            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0 || vector[j] == 0) continue;

                sum = (sum + matrix[i][j] * vector[j]) % MOD;
            }

            res[i] = sum;
        }

        return res;
    }
}