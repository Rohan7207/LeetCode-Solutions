class Solution {
    public int maximumLength(int[] nums) {

        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        for (long num : freq.keySet()) {

            // special case for 1
            if (num == 1) {
                ans = Math.max(ans, freq.get(1L) % 2 == 0
                        ? freq.get(1L) - 1
                        : freq.get(1L));
                continue;
            }

            int len = 0;
            long curr = num;

            while (freq.containsKey(curr) && freq.get(curr) >= 2) {

                len += 2;

                curr = curr * curr;
            }

            // center element
            if (freq.containsKey(curr)) {
                len++;
            }

            if (len % 2 == 0)
                len--;

            ans = Math.max(ans, len);
        }

        return ans;
    }
}