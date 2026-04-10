class Solution {
    public int[] twoSum(int[] nums, int target) {
        // int[] arr=new int[2];
        // for(int i=0;i<nums.length-1;i++){
        //     for(int j=i+1;j<nums.length;j++){
        //         if(nums[i]+nums[j]==target){
        //             arr[0]=i;
        //             arr[1]=j;
        //             break; 
        //         }
        //     }
        // }
        // return arr;

        //Key = value and value = index
        Map<Integer, Integer> pairIdx = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            if(pairIdx.containsKey(target - num)){
                return new int[]{i, pairIdx.get(target - num)};
            }
            pairIdx.put(num, i);
        }

        return new int[]{};
    }
}