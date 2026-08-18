class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        Set<Integer> set = new HashSet<>();
        for(int val : freq.values()) {
            if(!set.add(val)) {
                return false;
            }
        }

        return true;
    }
}