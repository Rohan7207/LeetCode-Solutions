class Solution {
    public int longestConsecutive(int[] nums) {
        // Base case: handle empty input
        if (nums == null || nums.length == 0)
            return 0;

        // Step 1: Add all numbers to a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestSequence = 0;

        // Step 2: Iterate through the set to find sequence starts
        for (int num : numSet) {
            // Check if 'num' is the start of a sequence
            // (i.e., num - 1 is NOT in the set)
            if (!numSet.contains(num - 1)) {
                int currNum = num;
                int currSequence = 1;

                // Step 3: Expand the sequence as far as possible
                while (numSet.contains(currNum + 1)) {
                    currNum++;
                    currSequence++;
                }

                // Step 4: Keep track of the maximum length found
                longestSequence = Math.max(longestSequence, currSequence);
            }
        }

        return longestSequence;
    }
}


/*
// Time Complexity: O(n log n)
// Space Complexity: O(1) or O(log n) depending on sorting

// “I sort the array and count consecutive increasing sequences while handling duplicates separately.”

        if (nums.length == 0) return 0;

        Arrays.sort(nums);
        int curr = 1;
        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                curr++;
            } else if (nums[i] != nums[i - 1]) {
                curr = 1;
            }

            max = Math.max(max, curr);
        }

        return max;
    
    }
*/