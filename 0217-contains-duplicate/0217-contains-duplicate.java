class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for(int num : nums) {
            if(!seen.add(num)) {
                return true;
            }
        }

        return false;
    }
}

//The set.add(i) method doesn't just add an element—it also returns a boolean telling you if the addition was successful.
//If add(i) returns false (meaning a duplicate was found), !false becomes true.
