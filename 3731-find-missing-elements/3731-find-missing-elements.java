class Solution {
    public List<Integer> findMissingElements(int[] nums) {
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

        List<Integer> ans = new ArrayList<>();

        for(int i = min + 1; i < max; i++) {
            if(!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }
}