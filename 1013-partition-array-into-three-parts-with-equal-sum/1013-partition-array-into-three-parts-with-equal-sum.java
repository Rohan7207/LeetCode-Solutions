class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        if (totalSum % 3 != 0)
            return false;

        int target = totalSum / 3;
        int count = 0;
        int currSum = 0;

        for (int num : arr) {
            currSum += num;

            if (currSum == target) {
                currSum = 0;
                count++;
            }
        }

        return count >= 3;
    }
}