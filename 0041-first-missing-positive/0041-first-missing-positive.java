class Solution {
    public int firstMissingPositive(int[] nums) {
        // O(n) and O(1) = index placement / cyclic sort idea

        int i = 0;

        while(i < nums.length) {
            int correct = nums[i] - 1;

            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correct]) {
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            } else {
                i++;
            }
        }

        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}

/*

        Why return n + 1?

        Example: [1,2,3]
        All numbers: 1 to n exist.
        So missing number becomes: n + 1 = 4

        Time: O(n) and O(n)

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

        Another approach:

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