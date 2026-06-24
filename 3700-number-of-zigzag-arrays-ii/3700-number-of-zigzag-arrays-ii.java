// Problem: Number of ZigZag Arrays II
// Link: https://leetcode.com/problems/number-of-zigzag-arrays-ii/?envType=daily-question&envId=2026-06-24
// Difficulty: Hard

// Approach:
//
// We need to count ZigZag arrays of length n using values from l to r.
//
// First, notice that actual values do not matter.
// Only the number of possible values matters:
//
//     m = r - l + 1
//
// The ZigZag condition means:
//     no increasing-increasing pattern
//     no decreasing-decreasing pattern
//
// So directions must alternate:
//
//     UP, DOWN, UP, DOWN...
//     or
//     DOWN, UP, DOWN, UP...
//
// We use DP states:
//
//     up[i]   = number of valid arrays ending at value i
//               where the last move was UP
//
//     down[i] = number of valid arrays ending at value i
//               where the last move was DOWN
//
// For length 2:
//
//     up[i] = number of values smaller than i
//           = i
//
//     down[i] = number of values greater than i
//             = m - 1 - i
//
// For normal DP:
//
//     newUp[curr] = sum of down[prev] where prev < curr
//     newDown[curr] = sum of up[prev] where prev > curr
//
// But in ZigZag Arrays II, n can be very large.
// So we cannot build length 3, 4, 5 ... n one by one.
//
// Since the transition from one length to next length is always the same,
// we convert this DP transition into a matrix.
//
// We combine up[] and down[] into one state vector:
//
//     state[0 ... m - 1]       = up[]
//     state[m ... 2m - 1]      = down[]
//
// Then build a transition matrix T such that:
//
//     nextState = T * currentState
//
// For every curr:
//
//     oldDown[prev] contributes to newUp[curr]
//     when prev < curr
//
//     oldUp[prev] contributes to newDown[curr]
//     when prev > curr
//
// Since state for length 2 is already known,
// state for length n is:
//
//     T^(n - 2) * stateLength2
//
// We calculate T^(n - 2) using matrix exponentiation.
//
// Finally, sum all values in finalState.
// Because a valid ZigZag array can end with either UP or DOWN.

// Time Complexity: O((2m)^3 * log n)
// Space Complexity: O((2m)^2)
//
// where:
//     m = r - l + 1


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
