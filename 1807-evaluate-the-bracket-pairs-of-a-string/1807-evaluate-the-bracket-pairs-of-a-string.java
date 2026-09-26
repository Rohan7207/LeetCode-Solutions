// Problem: Evaluate the Bracket Pairs of a String
// Link: https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/?envType=daily-question&envId=2026-09-26
// Difficulty: Medium

// Approach:
//
// 1. Store every key-value pair from knowledge in a HashMap.
//      key → value
//
// 2. Traverse the string from left to right.
//
// 3. If the current character is normal text:
//      → append it directly to the result.
//
// 4. If the current character is '(':
//      → move forward until ')'.
//      → the characters between them form the key.
//
// 5. Look up the key in the HashMap.
//      - Present → append its value.
//      - Missing → append "?"
//
// 6. Move i to the closing ')' so the bracket expression is not
//    processed again.

// Time Complexity: O(n + K)
// Space Complexity: O(n + K)
//
// n = length of string
// K = total size of knowledge


class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < knowledge.size(); i++) {
            map.put(knowledge.get(i).get(0), knowledge.get(i).get(1));
        }

        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                StringBuilder temp = new StringBuilder();
                int idx = i + 1;

                while (idx < n && s.charAt(idx) != ')') {
                    temp.append(s.charAt(idx));
                    idx++;
                }

                String key = temp.toString();
                String value = map.getOrDefault(key, "?");

                res.append(value);
                i = idx;
            } else {
                res.append(ch);
            }
        }


        return res.toString();
    }
}
