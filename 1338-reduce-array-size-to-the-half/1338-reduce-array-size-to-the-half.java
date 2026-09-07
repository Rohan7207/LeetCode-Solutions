class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Extract frequencies and sort them in descending order
        List<Integer> frequencies = new ArrayList<>(freq.values());
        frequencies.sort(Collections.reverseOrder());

        int target = arr.length / 2;
        int removedElements = 0;
        int setCounts = 0;
        
        for (int count : frequencies) {
            removedElements += count;
            setCounts++;

            // As soon as we've removed at least half the array, we are done
            if (removedElements >= target) {
                break;
            }
        }

        return setCounts;
    }
}