class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] f = { 0, Integer.MIN_VALUE, Integer.MIN_VALUE };

        for (int num : nums) {
            int[] g = new int[3];
            System.arraycopy(f, 0, g, 0, 3);

            for (int i = 0; i < 3; i++) {
                g[(i + (num % 3)) % 3] = Math.max(g[(i + (num % 3)) % 3], f[i] + num);
            }

            f = g;
        }

        return f[0];
    }
}

/*
    f represents the answers before using the current number.
    g represents the answers after considering the current number.

    Why use f[i] + num, not g[i] + num?

Because we want the current num to be used only once.

If we used g, a value that already included num could potentially be used again during the same iteration.
*/