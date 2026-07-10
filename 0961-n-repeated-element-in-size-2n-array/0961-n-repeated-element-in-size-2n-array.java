class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length / 2;

        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (value == n) {
                return key;
            }
        }

        return 0;
    }
}