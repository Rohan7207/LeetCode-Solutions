class Solution {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int m = arr[(n - 1) / 2];

        int[] ans = new int[k];
        int idx = 0;
        int left = 0, right = n - 1;

        while(left <= right && idx < k) {
            int dist1 = Math.abs(arr[left] - m);
            int dist2 = Math.abs(arr[right] - m);

            if(dist1 > dist2) {
                ans[idx++] = arr[left++];
            } else if(dist1 < dist2) {
                ans[idx++] = arr[right--];
            } else {
                if(arr[left] > arr[right]) {
                    ans[idx++] = arr[left++];
                } else {
                    ans[idx++] = arr[right--];
                }
            }
        }

        return ans;
    }
}

/*
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
*/