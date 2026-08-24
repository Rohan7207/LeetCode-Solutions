class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();

        // Start with the maximum possible value so any first pair updates it
        int minDiff = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length - 1; i++) {
            int currDiff = arr[i + 1] - arr[i];  // Since array is sorted, arr[i+1] >= arr[i]

            if(currDiff < minDiff) {
                // Found a smaller difference! Reset the list and update minDiff
                minDiff = currDiff;
                ans.clear();
                ans.add(List.of(arr[i], arr[i + 1]));
            } else if (currDiff == minDiff){
                // Found another pair with the exact same minimum difference
                ans.add(List.of(arr[i], arr[i + 1]));
            }
        }

        return ans;
    }
}