class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();
        int currentRemainder = 0;

        for (int num : nums) {
            // Shift left by 1 (multiply by 2), add the new bit, and keep it under 5
            currentRemainder = (currentRemainder * 2 + num) % 5;
            
            // If the remainder is 0, the prefix is divisible by 5
            ans.add(currentRemainder == 0);
        }

        return ans;
    }
}