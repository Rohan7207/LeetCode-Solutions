class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }

        Arrays.sort(nums);
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            // num is used already
            if (freq.get(num) == 0) {
                continue;
            }

            // num is the smallest currently unused number
            for (int j = 0; j < k; j++) {
                int curr = num + j;

                if (freq.getOrDefault(curr, 0) == 0) {
                    return false;
                }

                freq.put(curr, freq.get(curr) - 1);
            }

        }

        return true;
    }
}