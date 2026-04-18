class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums == null || nums.length < 4) return new ArrayList<>();

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for(int j = i + 1; j < n; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int k = j + 1;
                int l = n - 1;

                while(k < l) {
                    long sum = 0L + nums[i] + nums[j] + nums[k] + nums[l];

                    if(sum > target) {
                        l--;
                    }
                    else if(sum < target) {
                        k++;
                    }
                    else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));

                        while(k < l && nums[k] == nums[k + 1]) k++;
                        while(k < l && nums[l] == nums[l - 1]) l--;            

                        k++;
                        l--;
                    }
                } 
            }
        }

        return res;
    }
}

/*
Another approach
    Arrays.sort(nums);
        Set<List<Integer>> s=new HashSet<>();
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                int k=j+1;
                int l=nums.length-1;
                while(k<l){
                    long sum=nums[i];
                    sum+=nums[j];
                    sum+=nums[l];
                    sum+=nums[k];
                    if(sum==target){
                        s.add(Arrays.asList(nums[i],nums[j],nums[l],nums[k]));
                        k++;
                        l--;
                    }
                    else if(sum<target){
                        k++;
                    }else{
                        l--;
                    }
                }
            }
        }

        res.addAll(s);
        return res;
*/