class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        // Step 1: Convert array into +1 and -1
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {

            if (nums[i] == target) {
                prefix[i + 1] = prefix[i] + 1;
            } else {
                prefix[i + 1] = prefix[i] - 1;
            }
        }

        // Step 2: Coordinate compression
        int[] sorted = prefix.clone();

        Arrays.sort(sorted);

        List<Integer> unique = new ArrayList<>();

        for (int x : sorted) {

            if (unique.size() == 0 ||
                unique.get(unique.size() - 1) != x) {

                unique.add(x);
            }
        }

        // value -> rank mapping
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < unique.size(); i++) {

            map.put(unique.get(i), i + 1);
        }

        // Step 3: Fenwick Tree
        int[] fenwick = new int[unique.size() + 1];
        long ans = 0;

        // Add empty prefix sum = 0
        add(fenwick, map.get(0), 1);

        // Step 4: Process prefix sums
        for (int i = 1; i <= n; i++) {
            int current = prefix[i];
            int rank = map.get(current);

            // count previous prefix sums smaller than current
            ans += query(fenwick, rank - 1);

            // store current prefix
            add(fenwick, rank, 1);
        }

        return ans;
    }

    // Fenwick update
    private void add(int[] fenwick, int index, int value) {
        while (index < fenwick.length) {
            fenwick[index] += value;

            index += index & -index;
        }
    }

    // Fenwick prefix sum query
    private long query(int[] fenwick, int index) {
        long sum = 0;

        while (index > 0) {
            sum += fenwick[index];

            index -= index & -index;
        }

        return sum;
    }
}