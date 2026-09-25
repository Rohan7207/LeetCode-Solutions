class Solution {
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
}