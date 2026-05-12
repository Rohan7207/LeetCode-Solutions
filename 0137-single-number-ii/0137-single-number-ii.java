class Solution {
    public int singleNumber(int[] nums) {
        //Optimal appraoch with O(n) and O(1)
        int ones = 0, twos = 0;

        for(int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }

        return ones;
    }
}

/*
    //This is brute force approach with O(n) and O(n)
    Map<Integer, Integer> map = new HashMap<>();
    int res = 0;

    for(int num : nums) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
        if(entry.getValue() == 1) {
            res = entry.getKey();
            break;
        }
    }

    return res;



    //Bit Manipulation with O(n) or O(32*n) and O(1)
    int res = 0;

    for(int i = 0; i < 32; i++) {
        int count = 0;
        for(int num : nums) {
            if(((num >> i) & 1) == 1) {
                count++;
            }
        }

        if(count % 3 == 1) {
            res = res | (1 << i);
        }            
    }

    return res;
*/