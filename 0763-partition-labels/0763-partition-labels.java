// Problem: Partition Labels
// Link: https://leetcode.com/problems/partition-labels/
// Difficulty: Medium

// Approach:
// We need to divide the string into as many parts as possible
// such that each character appears in at most one part.
// Meaning:
//     If a character appears in one partition,
//     all occurrences of that character must be inside
//     that same partition.
// Step 1:
//     Store the last occurrence index of every character.
//     lastOccur[ch - 'a'] = last index of ch
// Why?
//     When we see a character,
//     we must make sure the current partition
//     extends at least till its last occurrence.
// Step 2:
//     Traverse the string again.
//     Maintain:
//         parStart -> starting index of current partition
//         parEnd   -> farthest last occurrence of characters
//                     seen in current partition
// For every index i:
//     Update partition end:
//         parEnd = max(parEnd, lastOccur[current character])
//     If i == parEnd:
//         It means all characters seen in this partition
//         end inside this partition.
//         So we can safely cut here.
//         Add partition length:
//             parEnd - parStart + 1
//         Start new partition from:
//             i + 1

// Time Complexity: O(n)
// Space Complexity: O(26) = O(1)


class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] lastOccur = new int[26]; 
        for (int i = 0; i < s.length(); i++) {
            lastOccur[s.charAt(i) - 'a'] = i;
        }
    
        List<Integer> res = new ArrayList<>();
        int parEnd = 0;
        int parStart = 0;

        for (int i = 0; i < s.length(); i++) {
            parEnd = Math.max(parEnd, lastOccur[s.charAt(i) - 'a']);

            if (i == parEnd) {
                res.add(parEnd - parStart + 1);
                parStart = i + 1; 
            }
        }

        return res;
    }
}
