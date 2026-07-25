class Solution {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;

        for(int num : nums) {
            // This number is already used once, thus we skip it.
            if(first == num || second == num || third == num) {
                continue;
            }

            if(first <= num) {
                third = second;
                second = first;
                first = num;
            } else if(second <= num) {
                third = second;
                second = num;
            } else if(third <= num) {
                third = num;
            }
        }

        if(third == Long.MIN_VALUE) {
            return (int) first;
        }

        return (int) third;
    }
}

/*
    Follow up question : After giving the previous approach, the interviewer might come up with a restriction, that our environment doesn't support long, big integers, etc.
We used long integer variable because the minimum possible value in the input array was −2 
31
 , and initially, we need to store a value lower than this and used it to check if thirdMax was updated or not.
    class Solution {
    public int thirdMax(int[] nums) {
        Pair<Integer, Boolean> firstMax = new Pair<Integer, Boolean>(-1, false);
        Pair<Integer, Boolean> secondMax = new Pair<Integer, Boolean>(-1, false);
        Pair<Integer, Boolean> thirdMax = new Pair<Integer, Boolean>(-1, false);
        
        for (int num : nums) {
            // If current number is already stored, skip it.
            if ((firstMax.getValue() && firstMax.getKey() == num) || 
                (secondMax.getValue() && secondMax.getKey() == num) || 
                (thirdMax.getValue() && thirdMax.getKey() == num)) {
                continue;
            }
            
            // If we never stored any variable in firstMax
            // or curr num is bigger than firstMax, then curr num is the biggest number.
            if (!firstMax.getValue() || firstMax.getKey() <= num) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = new Pair<Integer, Boolean>(num, true);
            }
            // If we never stored any variable in secondMax
            // or curr num is bigger than secondMax, then curr num is 2nd biggest number.
            else if (!secondMax.getValue() || secondMax.getKey() <= num) {
                thirdMax = secondMax;
                secondMax = new Pair<Integer, Boolean>(num, true);
            }
            // If we never stored any variable in thirdMax
            // or curr num is bigger than thirdMax, then curr num is 3rd biggest number.
            else if (!thirdMax.getValue() || thirdMax.getKey() <= num) {
                thirdMax = new Pair<Integer, Boolean>(num, true);
            }
        }
        
        // If third max was never updated, it means we don't have 3 distinct numbers.
        if (!thirdMax.getValue()) {
            return firstMax.getKey();
        }
        
        return thirdMax.getKey();
    }
}
*/