class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] freqP = new int[26];

        if(p.length() > s.length()) {
            return ans;
        }

        for(char ch : p.toCharArray()) {
            freqP[ch - 'a']++;
        }

        int left = 0;
        int[] freq = new int[26];
        int windowSize = 0;
        int n = p.length();
        for(int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'a']++;
            windowSize++;

            if(windowSize > n) {
                freq[s.charAt(left) - 'a']--;
                left++;
                windowSize--;
            }

            if(windowSize == n) {
                boolean flag = true;
                for(int i = 0; i < 26; i++) {
                    if(freq[i] != freqP[i]) {
                        flag = false;
                    }
                }

                if(flag) {
                    ans.add(left);
                }
            }
        }

        return ans;
    }
}