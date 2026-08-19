class Solution {
    public int findLucky(int[] arr) {
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
    }
}