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

/*
   // 39ms beats 29.03%
    class Node {
        Node[] children;
        boolean eow;

        public Node() {
            children = new Node[26];
            for(int i = 0; i < 26; i++) {
                children[i] = null;
            }

            eow = false;
        }
    }

    Node root;
    public Trie() {
        root = new Node();
    }    

    public void insert(String word) {
        Node cur = root;
        for(int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if(cur.children[idx] == null) {
                cur.children[idx] = new Node();
            }

            if(i == word.length() - 1) {
                cur.children[idx].eow = true;
            }

            cur = cur.children[idx];
        }
    }
    
    public boolean search(String word) {
        Node cur = root;
        for(int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if(cur.children[idx] == null) {
                return false;
            }

            if(i == word.length()-1 && cur.children[idx].eow == false) {
                return false;
            }

            cur = cur.children[idx];
        }

        return true;
    }
    
    public boolean startsWith(String prefix) {
        Node cur = root;
        for(int i = 0; i < prefix.length(); i++){
            int idx = prefix.charAt(i) - 'a';

            if(cur.children[idx] == null){
                return false;
            }

            cur = cur.children[idx];
        }

        return true;    
    }
*/