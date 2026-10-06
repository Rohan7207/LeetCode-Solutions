class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int missingOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    missingOpen++;
                }
            }
        }

        return open + missingOpen;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)

// Traverse the string once while counting unmatched opening parentheses and unmatched closing parentheses, then return their total.

/*
    🔑 Key Observation

There are only two situations that require insertions:

A ')' appears without any unmatched '('.
Some '(' remain unmatched after the traversal.

✨ Magic Line / Important Line
return open + missingOpen;

The answer is simply the sum of:

remaining unmatched '('
unmatched ')' that needed an opening parenthesis.
💡 How We Thought to Derive the Solution

Instead of actually inserting parentheses, simulate matching them.

Every '(' waits for a future ')'.
Every ')' either matches an existing '(' or requires inserting a new '('.

After the traversal, any unmatched '(' each need one closing parenthesis.

✅ Why It Works
open always represents unmatched opening parentheses.
missingOpen counts every closing parenthesis that could not be matched.
Every unmatched parenthesis requires exactly one insertion.
Thus, open + missingOpen gives the minimum insertions needed.
🧩 Pattern Recognition
Parentheses Matching
Greedy
Counter Simulation
Stack Optimization (using counters instead of an actual stack)

⭐ Interview Importance

⭐⭐⭐⭐☆

Tests understanding of:

Parentheses balancing
Greedy thinking
Stack optimization
Counter-based simulation

📚 Similar Problems
LeetCode 20 – Valid Parentheses
LeetCode 1541 – Minimum Insertions to Balance a Parentheses String
LeetCode 32 – Longest Valid Parentheses
LeetCode 1249 – Minimum Remove to Make Valid Parentheses
*/