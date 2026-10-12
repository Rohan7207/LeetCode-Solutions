// Problem: Making File Names Unique
// Link: https://leetcode.com/problems/making-file-names-unique/
// Difficulty: Medium

// Approach:
// 1. Use a HashMap to track used folder names and their last suffix values.
// 2. Traverse each name in the input array.
// 3. If the name is unused, mark it as used with suffix 0 and add it to the answer.
// 4. If the name already exists, start with the next suffix: map.get(name) + 1.
// 5. Generate candidate names in the format name(k) until an unused name is found.
// 6. Update the suffix for the original name and mark the generated candidate as used.
// 7. Store the candidate in the answer array.
// 8. Return the answer array.

// Time Complexity: O(n * L) average, where n is the number of names
// and L represents the cost of generating/checking candidate strings.
// Space Complexity: O(n * L) for the HashMap and answer array.


class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        String[] ans = new String[names.length];
        int idx = 0;

        for (String name : names) {
            if (!map.containsKey(name)) {
                map.put(name, 0);
                ans[idx++] = name;
            } else {
                int nextSuffix = map.get(name) + 1;
                String candidate = name + "(" + nextSuffix + ")";

                while (map.containsKey(candidate)) {
                    nextSuffix++;
                    candidate = name + "(" + nextSuffix + ")";
                }

                map.put(name, nextSuffix);
                map.put(candidate, 0);
                ans[idx++] = candidate;
            }
        }

        return ans;
    }
}
