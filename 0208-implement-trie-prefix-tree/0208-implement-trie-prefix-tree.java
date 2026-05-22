// Problem : Implement Trie Prefix Tree
// Link : https://leetcode.com/problems/implement-trie-prefix-tree/
// Difficulty : Medium

// Approach:
// Use a Trie (Prefix Tree) where each node stores:
//     - an array of 26 child references
//     - a flag indicating end of a word
//
// insert():
//     - Start from root.
//     - For each character in the word,
//       move to its child node.
//     - Create the child if it does not exist.
//     - After processing all characters,
//       mark the last node as end of word.
//
// search():
//     - Start from root.
//     - Traverse character by character.
//     - If any required child is missing,
//       return false.
//     - After reaching the last character,
//       return whether the node is marked
//       as end of word.
//
// startsWith():
//     - Traverse the prefix characters.
//     - If any required child is missing,
//       return false.
//     - If traversal succeeds,
//       return true since the prefix exists.

// Time Complexity:
//     insert     : O(L)
//     search     : O(L)
//     startsWith : O(L)
//
// Space Complexity:
//     O(total characters inserted)


class Trie {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
        }
        node.isEnd = true;
    }

    boolean search(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (node.children[idx] == null) {
                return false;
            }
            node = node.children[idx];
        }

        return node.isEnd == true;
    }

    boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';

            if (node.children[idx] == null) {
                return false;
            }

            node = node.children[idx];
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
