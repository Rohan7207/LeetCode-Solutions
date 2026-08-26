class Solution {
    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;
        int[] count = new int[4];

        for (char c : s.toCharArray()) {
            count[index(c)]++;
        }

        boolean balanced = true;

        for (int c : count) {
            if (c > target) {
                balanced = false;
                break;
            }
        }

        if (balanced) {
            return 0;
        }

        int left = 0;
        int ans = n;
        for (int right = 0; right < n; right++) {
            // Remove s[right] from outside
            count[index(s.charAt(right))]--;

            // check whether outside is valid
            while (left <= right && isValid(count, target)) {
                ans = Math.min(ans, right - left + 1);

                // put s[left] back outside
                count[index(s.charAt(left))]++;
                left++;
            }
        }

        return ans;
    }

    private boolean isValid(int[] count, int target) {
        return count[0] <= target &&
                count[1] <= target &&
                count[2] <= target &&
                count[3] <= target;
    }

    private int index(char c) {
        switch (c) {
            case 'Q':
                return 0;
            case 'W':
                return 1;
            case 'E':
                return 2;
            default:
                return 3;
        }
    }
}