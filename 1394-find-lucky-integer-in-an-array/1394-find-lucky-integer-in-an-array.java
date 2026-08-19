class Solution {
    public int findLucky(int[] arr) {
        int[] count = new int[501];

        for(int num : arr) {
            count[num]++;
        }

        for(int i = count.length - 1; i > 0; i--) {
            if(count[i] != 0 && count[i] == i) {
                return i;
            }
        }

        return -1;
    }
}

/*
    Using Map:
    Map<Integer, Integer> freq = new HashMap<>();

        for(int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1); 
        }

        int ans = -1;
        for(int num : freq.keySet()) {
            if(freq.get(num) == num) {
                ans = Math.max(ans, num);
            }
        }

        return ans;
*/