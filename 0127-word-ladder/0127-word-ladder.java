// Problem: Word Ladder
// Link: https://leetcode.com/problems/word-ladder/
// Difficulty: Hard

// Approach:
// Use Bidirectional BFS to find the shortest transformation
// sequence from beginWord to endWord.
// Maintain two sets:
//     - beginSet starting from beginWord
//     - endSet starting from endWord
// Always expand the smaller set for optimization.
// For each word in current level:
//     - Try replacing every character with 'a' to 'z'.
//     - Generate a new transformed word.
//     - If transformed word exists in opposite set,
//       shortest path is found.
//     - If transformed word exists in dictionary
//       and is not visited:
//           - mark visited
//           - add to next level set
// Move to next level and increment steps.
// Return 0 if transformation is not possible.

// Time Complexity: O(N * L * 26)
// Space Complexity: O(N)
//
// N = number of words
// L = word length


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        int steps = 1;

        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            if(beginSet.size() > endSet.size()){
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextSet = new HashSet<>();

            for(String word : beginSet){
                for(int i = 0; i < word.length(); i++){
                    for(char c = 'a'; c <= 'z'; c++){
                        if(c == word.charAt(i)) continue;

                        String newWord = word.substring(0, i) + c + word.substring(i + 1);
                        if(endSet.contains(newWord)) return steps + 1;

                        if(wordSet.contains(newWord) && !visited.contains(newWord)){
                            visited.add(newWord);
                            nextSet.add(newWord);
                        }
                    }
                }
            }

            beginSet = nextSet;
            steps++;
        }   

        return 0;
    }
}
