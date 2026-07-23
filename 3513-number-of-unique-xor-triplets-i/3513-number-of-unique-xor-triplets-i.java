class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n <= 2) return n;

        int ans = 1;
        while (ans <= n) {
            ans <<= 1; // ans = ans * 2 
        }

        return ans;
    }
}

/*
ans = 1   (1 <= 5) ✓
ans = 2   (2 <= 5) ✓
ans = 4   (4 <= 5) ✓
ans = 8   (8 <= 5) ✗ Stop
*/