class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; //Minimum possible open parenthesis
        int maxOpen = 0; //Maxmium possible open parenthesis

        for (char c : s.toCharArray()) {
            if (c == '(') {
                //Treat '(' as an open parenthesis
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                //Treat as ')' as an Closing parenthesis
                minOpen--;
                maxOpen--;
            } else {
                //treat as '*' that can be '(',')' or " "
                minOpen--; // Treat '*' as ')'
                maxOpen++; //Treat '*' as '('
            }

            //If at any point maxOpen is -ve it means there are to many ')'
            if (maxOpen < 0) {
                return false;
            }

            //minOpen should never be -ve ae we cannot match '(' with ')'
            minOpen = Math.max(minOpen, 0);
        }

        //If minOpen is 0 it means all '(' can be matched with ')'
        return minOpen == 0;
    }
}

/*
    //greedy algorithm with 
    We use two pointers maxOpen and minOpen 
    1.If there is open bracket increment both side
    2.if ) is decrement both
    3.if * we can treat ( increment maxOpen and if ) decrement minOpen
        also we can treat as null string in case ((*))
    4.if maxOpen is -ve return false  and if minOpen is 0 return 0
    Consider (((**)
    Steps 1.c=( , min=1,max=1
    2.c=( , min=2,max=2
    3.c=( , min=3,max=3
    4.c=* , min=2,max=4
    5.c=* , min=1,max=5
    6.c=),  min=0,max=6
    Since min=0 we return true
*/