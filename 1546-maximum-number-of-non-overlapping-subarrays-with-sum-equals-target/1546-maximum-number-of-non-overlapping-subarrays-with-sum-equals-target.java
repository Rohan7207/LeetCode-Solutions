class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        set.add(0);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            if (set.contains(prefixSum - target)) {
                count++;

                set.clear();
                set.add(0);
                prefixSum = 0;
            } else {
                set.add(prefixSum);
            }
        }

        return count;
    }
}