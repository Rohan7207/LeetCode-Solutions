class Solution {
    public int maxPower(String s) {
        int count = 1;
        int ans = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                count++;
            } else {
                count = 1;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}

/*
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
*/