class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        for(int num : nums1) {
            if(num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            }
        }

        boolean possibleOdd = true;

        //  Try making everything odd
        for(int num : nums1) {
            if(num % 2 == 0 && num - minOdd < 1) {
                possibleOdd = false;
            }
        }

        boolean possibleEven = minOdd == Integer.MAX_VALUE ? true : false;

        return possibleOdd || possibleEven;
    }
}