class Solution {
    public int secondHighest(String s) {
        int digitsFound = 0;

        for (char c = '9'; c >= '0'; c--) {
            if (s.indexOf(c) != -1) {
                digitsFound++;

                if (digitsFound == 2) {
                    return c - '0';
                }
            }
        }

        return -1;
    }
}

/*
      StringBuilder sb = new StringBuilder();

        for(char ch : s.toCharArray()) {
            if(Character.isDigit(ch)) {
                sb.append(ch);
            }
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for(int i = 0; i < sb.length(); i++) {
            int curr = sb.charAt(i) - '0';
            if(first == curr || second == curr) {
                continue;
            }

            if(first < curr) {
                second = first;
                first = curr;
            } else if(second < curr) {
                second = curr;
            }
        }

        if(second == Integer.MIN_VALUE) return -1;

        return second;
*/
/*
     int first = -1, second = -1;
        for(char c : s.toCharArray()){
            if(c >= '0' && c <= '9'){
                int n = c - '0';
                if(n > first){
                    second = first;
                    first = n;
                }
                else if(n > second && n < first) second = n;
            }
        }
        return second;
*/