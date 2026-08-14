class Solution {
    public int maximumLengthSubstring(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int ans = 0;

        int left = 0;
        for(int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            while(freqMap.get(ch) > 2) {
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}