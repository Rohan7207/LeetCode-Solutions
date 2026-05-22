// Problem : Design Add and Search Words Data Structure
// Link : https://leetcode.com/problems/design-add-and-search-words-data-structure/
// Difficulty : Medium

// Approach:
// Use a Trie data structure to store words efficiently.
// Each Trie node contains:
//     - children array of size 26
//     - eow flag to mark end of word
//
// addWord():
//     - Start from root.
//     - For each character, create a child node
//       if it does not already exist.
//     - Move to the child node.
//     - After inserting all characters,
//       mark eow as true.
//
// search():
//     - Use DFS to search the word in Trie.
//     - If current character is normal letter,
//       move to its matching child.
//     - If current character is '.',
//       try all possible non-null children.
//     - If any path returns true,
//       word exists.
//     - After processing all characters,
//       return whether current node is end of word.

// Time Complexity:
//     addWord: O(L)
//     search: O(26^D * L) in worst case
//
// Space Complexity: O(N * L)


class Node{
    Node[] children = new Node[26];
    boolean eow = false;
}

class WordDictionary {

    private static Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node node = root;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if(node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Node();
            }

            node = node.children[ch - 'a'];
        }

        node.eow = true;
    } 

    public boolean search(String word) {
        return dfs(word, root, 0);
    }

    public boolean dfs(String word, Node curr, int idx) {
        Node node = curr;

        for(int i = idx; i < word.length(); i++) {
            char ch = word.charAt(i);

            if(ch == '.') {
                for(Node child : node.children) {
                    if(child != null && dfs(word, child, i + 1)) return true;
                }

                return false;
            } else {
                if(node.children[ch - 'a'] == null) return false;

                node = node.children[ch - 'a'];
            }
        }

        return node.eow;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
