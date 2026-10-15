class Solution {
    public int sumCounts(List<Integer> nums) {
        int sum = 0;
        int n = nums.size();

        for (int i = 0; i < n; i++) {
            int count = 0;
            boolean[] vis = new boolean[101];

            for (int j = i; j < n; j++) {
                int num = nums.get(j);
                if (!vis[num]) {
                    vis[num] = true;
                    count++;
                }

                sum += count * count;
            }
        }

        return sum;
    }
}

/*
    public int sumCounts(List<Integer> nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;

        for (int i = 0; i < nums.size(); i++) {
            int count = 1;
            sum += count;
            set.add(nums.get(i));
            for (int j = i + 1; j < nums.size(); j++) {
                if (set.add(nums.get(j))) {
                    count++;
                }

                sum += count * count;
            }
            
            set.clear();
        }

        return sum;
    }
*/