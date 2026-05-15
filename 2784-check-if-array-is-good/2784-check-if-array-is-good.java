class Solution {
    public boolean isGood(int[] nums) {
        int n = 0;

        for(int num : nums) {
            n = Math.max(n, num);
        }

        if(nums.length != n + 1) return false;

        int[] freq = new int[201];

        for(int num : nums) {
            freq[num]++;
        }

        // Every number from 1 to n-1 appears exactly once
        for(int i = 1; i < n; i++) {
            if(freq[i] != 1) return false;
        }

        return freq[n] == 2;
    }
}

/*
    Set<Integer> seen = new HashSet<>();
        int n = nums.length - 1;
        boolean flag = false;

        for(int num : nums) {
            if(num > n) return false;

            if(seen.contains(num)) {
                if(num < n || flag) return false;
                flag = true;
                continue;
            }

            seen.add(num);
        }

        return true;
*/