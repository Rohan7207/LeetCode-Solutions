class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Count the frequency of each number
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Put all the frequencies into a list and sort them in ascending order
        List<Integer> frequencies = new ArrayList<>(freqMap.values());
        Collections.sort(frequencies);

        // Greedily remove the least frequent elements
        int uniqueElements = freqMap.size();

        for (int count : frequencies) {
            if (k >= count) {
                k -= count; // Deduct the counts from k
                uniqueElements--; // One unique integer is completely removed
            } else {
                // If we don't have enough k to eliminate this integer, we stop.
                break;
            }
        }

        return uniqueElements;
    }
}