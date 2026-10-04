class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; //Minimum possible open parenthesis
        int maxOpen = 0; //Maxmium possible open parenthesis

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
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

// Time Complexity: O(n)
// Space Complexity: O(1)

// “I greedily maintain the minimum and maximum possible number of open parentheses after each character, treating * as the most flexible character.”

/*
    Key Observation

For:

*

we don't know whether it is:

(
)
empty

Trying all possibilities would be:

Exponential

Instead:

Track a range of possibilities.
What do minOpen and maxOpen mean?

Suppose after reading some prefix:

(*(

Possible open counts might be:

1
2
3

Instead of storing all values:

Store:

minOpen = 1
maxOpen = 3
Magic Lines
For '('
minOpen++;
maxOpen++;

Definitely creates one more unmatched '('.

For ')'
minOpen--;
maxOpen--;

Definitely closes one open bracket.

For '*'
minOpen--;
maxOpen++;

Why?

Treat '*' as ')'
    ↓
minimum open decreases

Treat '*' as '('
    ↓
maximum open increases
Invalid Case
if (maxOpen < 0)
    return false;

If even the maximum possible open count becomes negative:

Too many ')'

No interpretation can save us.

Important Line
minOpen = Math.max(minOpen, 0);

Open brackets can never be negative.

Example:

*

Treating '*' as ')':

minOpen = -1

Not meaningful.

So clamp it:0

Pattern Recognition

Whenever you see:

Parentheses
Wildcard Character
Multiple Interpretations
Validity Check

Think:

Greedy Range Tracking

instead of:

Backtracking
Interview Importance

🔥 Very High

Classic greedy interview problem.

Tests:

Greedy reasoning
Range maintenance
Avoiding exponential recursion

Similar Problems
678. Valid Parenthesis String
20. Valid Parentheses
1249. Minimum Remove to Make Valid Parentheses
2116. Check if a Parentheses String Can Be Valid

Common Pattern
Wildcard Choices
       ↓
Track Min/Max Possibilities
       ↓
Greedy Validation
*/