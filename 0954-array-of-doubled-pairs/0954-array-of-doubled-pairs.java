class Solution {
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int n = arr.length;

        Integer[] nums = new Integer[n];

        for (int i = 0; i < n; i++) {
            nums[i] = arr[i];
        }

        Arrays.sort(nums, (a, b) -> Integer.compare(Math.abs(a), Math.abs(b)));

        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        for (int x : nums) {
            if (freqMap.get(x) == 0) {
                continue;
            }

            // Is there at least one unused double available
            if (freqMap.getOrDefault(2 * x, 0) == 0) {
                return false;
            }

            freqMap.put(x, freqMap.get(x) - 1);
            freqMap.put(2 * x, freqMap.get(2 * x) - 1);
        }

        return true;
    }
}