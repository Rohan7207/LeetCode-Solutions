class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean allEven = true;

        for(int num : nums1) {
            min = Math.min(min, num);

            if(num % 2 == 1) {
                allEven = false;
            }
        }

        //minimum is odd->always possible and if minimum is even -> all elements must be even 
        return allEven || min % 2 == 1;
    }
}

/*
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        for(int num : nums1) {
            if(num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            }
        }

        boolean possibleOdd = true;

        //  Try making every even to odd
        for(int num : nums1) {
            if(num % 2 == 0 && num - minOdd < 1) {
                possibleOdd = false;
            }
        }

        boolean possibleEven = minOdd == Integer.MAX_VALUE ? true : false;

        return possibleOdd || possibleEven;
    }
*/