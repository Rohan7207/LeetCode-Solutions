class Solution {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int m = arr[(n - 1) / 2];

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int distA = Math.abs(a - m);
            int distB = Math.abs(b - m);

            if (distA != distB) {
                return Integer.compare(distA, distB);
            }

            return Integer.compare(a, b);
        });

        // 11 8 7 7 6 6  m = 7
        for(int i = 0; i < n; i++) {
            pq.offer(arr[i]);

            if(pq.size() > k) {
                pq.poll();
            }
        }

        int[] ans = new int[k];
        int idx = 0;
        while(!pq.isEmpty()) {
            ans[idx++] = pq.poll();
        }

        return ans;
    }
}