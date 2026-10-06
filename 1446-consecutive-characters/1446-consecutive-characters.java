class Solution {
    public int maxPower(String s) {
        int count = 0;
        int ans = 0;

        for(int i = 0; i < s.length(); i++) {
            if(i == 0) {
                count++;
            } else {
                char prevChar = s.charAt(i - 1);
                char ch = s.charAt(i);

                if(prevChar != ch) {
                    count = 1;
                } else {
                    count++;
                }
            }

            ans = Math.max(ans, count);
        }

        

        return ans;
    }
}