class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean[] found = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] <= n) {
                found[nums[i]] = true; //it makes found[that num is found  and marks true]
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!found[i]) {
                return i; //if in found array any positive num is false return that num
            }
        }

        return n + 1; //returns last num
    }
}

/*
        Set<Integer> set = new HashSet<>();
        
        for (int i : nums) {
            set.add(i);
        }

        int i = 1;
        while (set.contains(i)) {
            i++;
        }

        return i;
*/