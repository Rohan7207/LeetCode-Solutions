class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int ans = 0;

        int left = 0;
        for (int right = 0; right < fruits.length; right++) {
            freqMap.put(fruits[right], freqMap.getOrDefault(fruits[right], 0) + 1);

            while (freqMap.size() > 2) {
                int currFreq = freqMap.get(fruits[left]);
                currFreq--;

                if (currFreq == 0) {
                    freqMap.remove(fruits[left]);
                } else {
                    freqMap.put(fruits[left], currFreq);
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}