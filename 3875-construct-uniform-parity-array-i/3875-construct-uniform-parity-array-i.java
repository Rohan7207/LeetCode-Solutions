class Solution {
    public boolean uniformArray(int[] nums1) {
        return true;
    }
}

/*
    public boolean uniformArray(int[] nums1) {
        int oddCount = 0;
        int evenCount = 0;

        for (int num : nums1) {
            if (num % 2 != 0) {
                oddCount++;
            } else {
                evenCount++;
            }
        }

        return oddCount > 0 || evenCount == nums1.length;
    }
*/