class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1); 
        }

        int ans = -1;
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int val = entry.getKey();
            int occurence = entry.getValue();

            if(val == occurence) {
                ans = Math.max(ans, val);
            }
        }

        return ans;
    }
}