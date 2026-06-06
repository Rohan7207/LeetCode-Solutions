class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}

// class Solution {
//     public List<Integer> findDisappearedNumbers(int[] nums) {
//         List<Integer> res = new ArrayList<>();

//         Set<Integer> s = new HashSet<>();

//         for (int i = 0; i < nums.length; i++) {
//             s.add(nums[i]);
//         }

//         for (int i = 1; i <= nums.length; i++) {
//             if (!s.contains(i)) {
//                 res.add(i);
//             }
//         }

//         return res;
//     }
// }