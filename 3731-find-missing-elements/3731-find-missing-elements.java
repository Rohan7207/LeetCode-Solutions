class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int num : nums) {
            if(num < min) {
                min = num;
            } 

            if(num > max) {
                max = num;
            }

            set.add(num);
        }

        for(int i = min; i <= max; i++) {
            if(set.contains(i)) {
                continue;
            } else {
                ans.add(i);
            }
        }

        return ans;
    }
}