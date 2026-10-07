class Solution {
    public int maxVowels(String s, int k) {
        int ans = 0;

        int left = 0;
        int count = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }

            if (right - left + 1 > k) {
                char leftChar = s.charAt(left);
                if (leftChar == 'a' || leftChar == 'e' || leftChar == 'i' || leftChar == 'o' || leftChar == 'u') {
                    count--;
                }

                left++;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}