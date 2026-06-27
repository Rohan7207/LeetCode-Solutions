class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int[] count = new int[nums.length + 1];

        for (int num : nums) {
            count[num]++;
        }

        for (int i = 1; i <= nums.length; i++) {
            if (count[i] == 2)
                ans[0] = i;

            if (count[i] == 0)
                ans[1] = i;
        }

        return ans;
    }
}

/*
    // Solution with hashmap
    Map<Integer, Integer> freq = new HashMap<>();

    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    int[] ans = new int[2];
    for (int i = 1; i <= nums.length; i++) {
        if (freq.containsKey(i)) {
            if (freq.get(i) == 2) {
                ans[0] = i;
            }
        } else {
            ans[1] = i;
        }
    }

    return ans;
*/