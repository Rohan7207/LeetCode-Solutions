class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int totalElements = 0;
        int maxSum = 0;

        // Group elements by their diagonal sum (i + j)
        for(int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);
            totalElements += row.size();

            for(int j = 0; j < row.size(); j++) {
                int sum = i + j;

                // If the sum list doesn't exist, create it; then add the value
                map.computeIfAbsent(sum, k -> new ArrayList<>()).add(row.get(j));

                maxSum = Math.max(maxSum, sum);
            }
        }

        // Build the final flat 1D array
        int[] res = new int[totalElements];
        int idx = 0;

        for(int sum = 0; sum <= maxSum; sum++) {
            List<Integer> diagonal = map.get(sum);

            if(diagonal != null) {
                for(int i = diagonal.size() - 1; i >= 0; i--) {
                    res[idx++] = diagonal.get(i);
                }
            }
        }

        return res;
    }
}