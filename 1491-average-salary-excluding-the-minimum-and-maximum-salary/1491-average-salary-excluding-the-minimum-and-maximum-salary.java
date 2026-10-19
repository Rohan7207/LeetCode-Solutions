class Solution {
    public double average(int[] salary) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int val : salary) {
            max = Math.max(max, val);
            min = Math.min(min, val);
        }

        int count = 0;
        int sum = 0;
        for (int i = 0; i < salary.length; i++) {
            int value = salary[i];
            if (value == max || value == min) {
                continue;
            }

            sum += value;
            count++;
        }

        return sum / (double) count;
    }
}

// Time Complexity: O(n), where n is salary.length.
// Space Complexity: O(1).