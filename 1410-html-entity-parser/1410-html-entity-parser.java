// Problem: HTML Entity Parser
// Link: https://leetcode.com/problems/html-entity-parser/
// Difficulty: Medium

// Approach:
// 1. Store all valid HTML entities and their decoded characters in a HashMap.
// 2. Traverse the string from left to right.
// 3. When '&' is found, scan forward until ';' to get the complete entity.
// 4. Check whether this entity exists in the HashMap.
// 5. If it exists, append its decoded value and jump i to the ';'.
// 6. Otherwise, append the current character normally.
// 7. Return the constructed StringBuilder.

// Time Complexity: O(n) because the entity length is bounded by a small constant.
// Space Complexity: O(n) for the output StringBuilder + HashMap.


class Solution {
    public String entityParser(String text) {
        int n = text.length();
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = text.charAt(i);

            if (ch == '&') {
                int idx = i;
                while (idx < n && text.charAt(idx) != ';') {
                    idx++;
                }

                if (idx < n) {
                    String substr = text.substring(i, idx + 1);
                    if (map.containsKey(substr)) {
                        ans.append(map.get(substr));
                        i = idx;
                        continue;
                    }
                }
            }
            
            ans.append(ch);
        }

        return ans.toString();
    }
}
