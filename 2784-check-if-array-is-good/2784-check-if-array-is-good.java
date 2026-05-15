class Solution {
    public boolean isGood(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int n = nums.length - 1;
        boolean flag = false;

        for(int num : nums) {
            if(num > n) return false;

            if(seen.contains(num)) {
                if(num < n || flag) return false;
                flag = true;
                continue;
            }

            seen.add(num);
        }

        return true;
    }
}