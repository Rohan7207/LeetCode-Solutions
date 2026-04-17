class Solution {
    public int romanToInt(String s) {
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            char currchar = s.charAt(i);

            if(i < s.length() - 1){
                char nextchar = s.charAt(i + 1);
            
                if(currchar == 'I' && (nextchar == 'V' || nextchar == 'X')){
                    num -= 1;
                    continue;
                }else if(currchar == 'X' && (nextchar == 'L' || nextchar == 'C')){
                    num -= 10;
                    continue;
                }else if(currchar == 'C' && (nextchar == 'D' || nextchar == 'M')){
                    num -= 100;
                    continue;
                }
            }
        
            if(currchar == 'I') num += 1;
            else if(currchar == 'V') num += 5;
            else if(currchar == 'X') num += 10;
            else if(currchar == 'L') num += 50;
            else if(currchar == 'C') num += 100;
            else if(currchar == 'D') num += 500;
            else if(currchar == 'M') num += 1000;
        }

        return num;
    }
}