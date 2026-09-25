class Solution {
    public int countLargestGroup(int n) {
        // Only 37 groups can come in n range
        int[] freq = new int[37];
        int maxSize = -1;

        for(int i = 1; i <= n; i++) {
            int temp = i;
            int sum = 0;

            while(temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }

            freq[sum]++;
            maxSize = Math.max(maxSize, freq[sum]);
        }

        int ans = 0;
        for(int val : freq) {
            if(val == maxSize) {
                ans++;
            }
        }

        return ans;
    }
}

/*
    public int countLargestGroup(int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int maxSize = -1;

        while(n > 0) {
            int temp = n;
            int sum = 0;

            while(temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }

            map.computeIfAbsent(sum, k -> new ArrayList<>()).add(temp);
            List<Integer> list = map.get(sum);
            maxSize = Math.max(maxSize, list.size());
            n--;
        }

        int ans = 0;
        for(Integer val : map.keySet()) {
            List<Integer> list = map.get(val);
            int size = list.size();

            if(size == maxSize) {
                ans++;
            }
        }

        return ans;
    }
*/