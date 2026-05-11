class Solution {
    Map<String, Integer> dist = new HashMap<>();
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //The BFS and DFS approach
        // Step 1 (BFS): Find the shortest distance from beginWord to all reachable words. We store these distances in a Map<String, Integer>.
        // Step 2 (DFS): Start from the endWord and work backwards to the beginWord.
        // Why backwards? It is more efficient to find the "parents" that lead to the target.
        // The Rule: Only move to a neighbor if its distance is exactly current distance - 1. This ensures every path is a shortest path.

        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) return res;

        // BFS: Fill the distance map
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        dist.put(beginWord, 0);

        while(!q.isEmpty()) {
            String curr = q.poll();
            if(curr.equals(endWord)) break; // Found shortest layer

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

        // DFS: Backtrack from end to beginning
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
            Collections.reverse(validPath); // Reverse because we went backwards
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

/*
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);

        // If endWord is not in the dictionary, no path is possible
        if(!wordSet.contains(endWord)) return result;

        // BFS Queue stores lists (paths) instead of single words
        Queue<List<String>> q = new LinkedList<>();
        q.offer(new ArrayList<>(Arrays.asList(beginWord)));

        boolean found = false;

        while(!q.isEmpty() && !found) {
            int size = q.size();
            // Words found in the CURRENT level are tracked here
            Set<String> visitedInThisLevel = new HashSet<>();

            for(int i = 0; i < size; i++) {
                List<String> path = q.poll();
                String lastWord = path.get(path.size() - 1);
            
                // Try all 1-character transformations
                for(int j = 0; j < lastWord.length(); j++) {
                    char[] chars = lastWord.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(chars[j] == c) continue;
                        chars[j] = c;
                        String nextWord = new String(chars);

                        if(wordSet.contains(nextWord)) {
                            // If we reach the target, save the path
                            if(nextWord.equals(endWord)) {
                                found = true;
                                List<String> newPath = new ArrayList<>(path);
                                newPath.add(nextWord);
                                result.add(newPath);
                            } else if(!found){
                                // If target not found yet, keep expanding
                                List<String> newPath = new ArrayList<>(path);
                                newPath.add(nextWord);
                                q.offer(newPath);
                                visitedInThisLevel.add(nextWord);
                            }
                        }  
                    }
                }
            } 
            
            // DELAYED DELETION: Only remove words after the entire level is done  
            wordSet.removeAll(visitedInThisLevel);
        } 

        return result;
*/

/* 
class solution {
    Map<String, Integer> dist = new HashMap<>();
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>>(wordList);
        if(!wordSet.contains(endWord)) return res;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        dist.put(beginword, 0);

        while(!q.isEmpty()) {
            String curr = q.poll();
            if(curr == endWord) break;

            char[] chars = curr.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                char oldLetter = chars[i];
                for(char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String nextWord = new String(chars);
                    if(wordSet.contains(nextWord) && !dist.containsKey(nextWord)) {
                        dist.put(nextWord, dist.get(curr) + 1);
                        q.offer(nextWord);
                    }
                }

                chars[i] = oldLetter;
            }
        }

        if(dist.containsKey(endWord)) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(dist, path, endWord, beginWord);
        }

        return res;
    }

    private void dfs(Map<String, Integer> dist, List<String> path, String curr, String start) {
        if(curr.equals(start)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            res.add(validPath);
            return;
        }


        char[] chars = curr.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            char oldLetter = chars[i];
            for(char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String nextWord = new String(chars);
                if(dist.containsKey(nextWord) && dist.get(nextWord) == dist.get(curr) - 1) {
                    path.add(curr);
                    dfs(dist, path, nextWord, start);
                    path.remove(path.size() - 1);
                }
            }

            chars[i] = oldWord;
        }
    }
}
*/