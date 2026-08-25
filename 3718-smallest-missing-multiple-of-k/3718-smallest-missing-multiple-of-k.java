class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int ans = k;
        while (set.contains(ans)) {
            ans += k;
        }

        return ans;
    }
}

/*
    for(int multiple=k;;multiple+=k){
            boolean found=false;
            for(int i=0;i<nums.length;i++){
                if(nums[i]==multiple){
                    found=true;
                    break;
                }
            }
            if(!found){
            return multiple;
            }
        }
*/