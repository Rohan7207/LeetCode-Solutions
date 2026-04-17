class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while(j < k){
                int total = nums[i] + nums[j] + nums[k];

                if(total > 0){
                    k--;
                }
                else if(total < 0){
                    j++;
                }
                else{
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    while(j < k && nums[j] == nums[j - 1]){
                        j++;
                    }
                }
            }
        }

        return res;
    }
}

/*
Another approach
    // int target = 0;
        // Arrays.sort(nums);
        // Set<List<Integer>> s = new HashSet<>();
        // List<List<Integer>> output = new ArrayList<>();

        // for(int i = 0; i < nums.length; i++){
        //     int j = i + 1;
        //     int k = nums.length - 1;
        //     while(j < k){
        //         int sum = nums[i] + nums[j] + nums[k];
        //         if(sum == target){
        //             s.add(Arrays.asList(nums[i],nums[j],nums[k]));
        //             j++;
        //             k--;
        //         }else if(sum < target) j++;
        //         else k--;
        //     }
        // }
        // output.addAll(s);
        // return output;
*/