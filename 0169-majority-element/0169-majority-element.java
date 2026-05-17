class Solution {
    public int majorityElement(int[] nums) {
        int count = 1;
        int candidate = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (candidate == nums[i])
                count++;
            else
                count--;

            if (count == 0) {
                candidate = nums[i];
                count = 1;
            }
        }

        return candidate;
    }
}

/*
    Code Explanation (Boyer–Moore Voting Algorithm)
        int count = 1;
        int element = nums[0];

        We assume the first element is the majority candidate
        count tracks how strongly this candidate is supported

        for(int i = 1; i < nums.length; i++)
        We traverse the array from the second element

        Case 1: Same element
        if(element == nums[i]) count++;
        If current number matches candidate → increase confidence

        Case 2: Different element
        else count--;
        Different number → “cancels out” one vote of the candidate

        Reset condition
        if(count == 0) {
        element = nums[i];
        count = 1;
        }
        If votes cancel completely:
        Choose new candidate
        Reset count to 1

        Final step
        return element;
        Remaining candidate is the majority element
        It survives because majority elements cannot be fully cancelled
*/