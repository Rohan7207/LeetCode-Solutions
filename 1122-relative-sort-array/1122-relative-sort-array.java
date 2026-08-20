class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr1) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[arr1.length];
        int k = 0;

        for(int i = 0; i < arr2.length; i++) {
            while(freq.get(arr2[i]) > 0) {
                res[k++] = arr2[i];

                freq.put(arr2[i], freq.get(arr2[i]) - 1);
            }
        }

        List<Integer> remaining = new ArrayList<>(freq.keySet());
        Collections.sort(remaining);

        for(int num : remaining) {
            while(freq.get(num) > 0) {
                res[k++] = num;
                freq.put(num, freq.get(num) - 1);
            }
        }

        return res;
    }
}