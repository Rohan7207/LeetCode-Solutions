class Solution {
    int getValue(char ch) {
        if(ch == 'I') return 1;
        else if(ch == 'V') return 5;
        else if(ch == 'X') return 10;
        else if(ch == 'L') return 50;
        else if(ch == 'C') return 100;
        else if(ch == 'D') return 500;
        return 1000;
    }

    public int romanToInt(String s) {
        int num = 0;

        for(int i = 0; i < s.length(); i++) {
            int curr = getValue(s.charAt(i));

            if(i < s.length() - 1) {
                int next = getValue(s.charAt(i + 1));

                if(curr < next) {
                    num -= curr;
                    continue;
                }
            }

            num += curr;
        }

        return num;
    }
}