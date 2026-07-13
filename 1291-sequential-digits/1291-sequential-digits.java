class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();

        String s = "123456789";
        List<Integer> ans = new ArrayList<>();

        for (int len = minLen; len <= maxLen; len++) {
            // Generate all sequential numbers of this length
            for (int i = 0; i <= 9 - len; i++) {
                String num = s.substring(i, i + len);
                int value = Integer.parseInt(num);
                
                if (value >= low && value <= high) {
                    ans.add(value);
                }
            }
        }

        return ans;
    }
}