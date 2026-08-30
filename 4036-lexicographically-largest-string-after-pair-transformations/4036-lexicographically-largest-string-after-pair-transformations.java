class Solution {
    public String[] largestString(int[] nums) {
        String[] ans = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = getLargestString(nums[i]);
        }

        return ans;
    }

    private String getLargestString(int x) {
        if (x == 0) {
            return "";
        }

        // frequencies[0] = count of 'a', frequencies[1] = count of 'b', ..., frequencies[25] = count of 'z'
        long[] freq = new long[26];
        freq[0] = x;

        // Cascade carries up from 'a' to 'y'
        // Note: frequencies[25] (the letter 'z') keeps all its pairs because "zz" cannot transform.
        for (int i = 0; i < 25; i++) {
            freq[i + 1] = freq[i] / 2;
            freq[i] = freq[i] % 2;
        }

        StringBuilder sb = new StringBuilder();

        // Construct the string from 'z' down to 'a' to ensure it's lexicographically largest
        for (int i = 25; i >= 0; i--) {
            char ch = (char) ('a' + i);
            long count = freq[i];

            // Append the character 'count' times
            for (long k = 0; k < count; k++) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}