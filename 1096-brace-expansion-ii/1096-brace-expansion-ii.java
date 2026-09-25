// Problem: Brace Expansion II
// Link: https://leetcode.com/problems/brace-expansion-ii/?envType=daily-question&envId=2026-09-25
// Difficulty: Hard

// Approach:
//
// 1. Think of the expression as two operations:
//      - Union:      {a,b}      → {"a","b"}
//      - Concatenation: {a,b}{c,d} → {"ac","ad","bc","bd"}
//
// 2. getUnit() parses one smallest unit:
//      - A letter → a Set containing that letter.
//      - {...}    → recursively call performUnion().
//
// 3. performConcat() handles consecutive units.
//      Start with {""} because "" is the identity for concatenation.
//      For every next unit, take the Cartesian product:
//          left + right
//
// 4. performUnion() handles expressions separated by ','.
//      Evaluate each part using performConcat()
//      and combine them using addAll().
//
// 5. idx is shared across all recursive calls.
//      Each parser method consumes exactly the characters it owns.
//
// 6. Finally, convert the Set to a List and sort it lexicographically,
//      because the problem requires sorted output.

// Time Complexity: O(K log K + total generated combinations)
// Space Complexity: O(K)
// where K is the number of distinct generated strings.


class Solution {

    String s;
    int n;
    int idx;

    public List<String> braceExpansionII(String expression) {
        n = expression.length();
        s = expression;
        idx = 0;

        Set<String> st = performUnion();
        List<String> res = new ArrayList<>(st);
        Collections.sort(res);

        return res;
    }

    private Set<String> getUnit() {
        Set<String> result = new HashSet<>();

        if (s.charAt(idx) == '{') {
            idx++; // '{'
            result = performUnion();

            idx++; // '}'
        } else { // Alphabet
            result.add(String.valueOf(s.charAt(idx)));
            idx++; // letter
        }

        return result;
    }

    private Set<String> performConcat() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (idx < n && (s.charAt(idx) == '{' || Character.isLetter(s.charAt(idx)))) {
            Set<String> temp = getUnit();

            Set<String> concatRes = new HashSet<>();

            for (String left : result) {
                for (String right : temp) {
                    concatRes.add(left + right);
                }
            }

            result = concatRes;
        }

        return result;
    }

    private Set<String> performUnion() {
        Set<String> result = new HashSet<>();

        while (true) {
            Set<String> temp = performConcat();

            result.addAll(temp);

            if (idx < n && s.charAt(idx) == ',') {
                idx++;
            } else {
                break;
            }
        }

        return result;
    }
}
