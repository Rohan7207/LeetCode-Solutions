class Solution {
    public int secondHighest(String s) {
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
    }
}