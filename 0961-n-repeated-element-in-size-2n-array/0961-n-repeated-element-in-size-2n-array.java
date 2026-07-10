class Solution {
    public int repeatedNTimes(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++)
            if (nums[i] == nums[i + 1] || nums[i] == nums[i + 2]) {
                return nums[i];
            }

        return nums[nums.length - 1];
    }
}

/*
    Why does this always work?

Suppose the repeated element is X.

Could every occurrence of X be at least 3 positions apart?

No.

For an array of length 2N with N copies of X, there simply isn't enough room to separate all copies by two different elements.

By the pigeonhole principle, at least one pair of X values must be:

adjacent (distance 1), or
separated by exactly one element (distance 2).

That's exactly what the code checks:

A[i] == A[i + 1]

or

A[i] == A[i + 2]

The only arrangement not caught by the loop is when the matching pair involves the last element, which is why returning A[A.length - 1] works.
*/

/*
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
*/