// Problem: Word Ladder II
// Link: https://leetcode.com/problems/word-ladder-ii/
// Difficulty: Hard

// Approach:
// Use BFS to find the shortest distance of every word
// from beginWord.
// Store distances in a map:
//     word -> shortest steps from beginWord
// BFS guarantees shortest path levels.
//
// After BFS:
//     - If endWord is reachable,
//       use DFS backtracking starting from endWord.
//     - During DFS:
//         - Generate all possible one-letter transformations.
//         - Move only to words whose distance is:
//               dist[curr] - 1
//           meaning one step closer to beginWord.
//     - Build valid shortest paths.
// Reverse paths before adding to result
// because DFS starts from endWord.

// Time Complexity: O(N * L * 26)
// Space Complexity: O(N)
//
// N = number of words
// L = word length


class Solution {
    Map<String, Integer> dist = new HashMap<>();
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) return res;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        dist.put(beginWord, 0);

        while(!q.isEmpty()) {
            String curr = q.poll();
            if(curr.equals(endWord)) break; 

            char[] chars = curr.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                char oldWord = chars[i];
                for(char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String nextWord = new String(chars);
                    if(wordSet.contains(nextWord) && !dist.containsKey(nextWord)) {
                        dist.put(nextWord, dist.get(curr) + 1);
                        q.offer(nextWord);
                    }
                }

                chars[i] = oldWord;
            }
        }

        if(dist.containsKey(endWord)) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, beginWord, path, dist);
        }

        return res;
    }

    private void dfs(String curr, String start, List<String> path, Map<String, Integer> dist) {
        if(curr.equals(start)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath); 
            res.add(validPath);
            return;
        }

        char[] chars = curr.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            char oldWord = chars[i];
            for(char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String nextWord = new String(chars);

                // The Magic: Only move to a word if it's 1 step closer to the start
                if(dist.containsKey(nextWord) && dist.get(nextWord) == dist.get(curr) - 1) {
                    path.add(nextWord);
                    dfs(nextWord, start, path, dist);
                    path.remove(path.size() - 1);
                }
            }

            chars[i] = oldWord;
        }
    }
}
