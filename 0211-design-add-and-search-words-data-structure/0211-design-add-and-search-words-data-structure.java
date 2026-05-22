//Trie Node Structure
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

            //if the node is null only, then we will add our character and create it's reference node
            if(node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Node();
            }

            //Now we will go to the reference of the node
            node = node.children[ch - 'a'];
        }

        //Atlast, we have completely inserted our word in the trie, so mark the flag as true
        node.eow = true;
    } 

    public boolean search(String word) {
        //Here we have created our dfs method which will search our word in the trie and 
        // it will handle the situation, if '.' comes in the word, it means we can have any words 
        // between a to z
        return dfs(word, root, 0);
    }

    public boolean dfs(String word, Node curr, int idx) {
        Node node = curr;

        //we will start traversing the entire word
        for(int i = idx; i < word.length(); i++) {
            char ch = word.charAt(i);

            //if the word contains '.', then we will traverse all the characters from a to z
            if(ch == '.') {
                //Traverse all the child of node
                for(Node child : node.children) {
                    //if the child is not null, then we will traverse the next characters of words
                    //if both are true then return true
                    if(child != null && dfs(word, child, i + 1)) return true;
                }

                //Atlast, if we traverse all the childs of node, and we did not get anything we will return false
                return false;
            } else {
                if(node.children[ch - 'a'] == null) return false;

                //else go to the refrence of that node
                node = node.children[ch - 'a'];
            }
        }

        //If we traverse the entire word, then return the eow
        return node.eow;
    }
}


/*
// 188ms beats 60.45%
   private Node root;

    public WordDictionary() {
        root=new Node();
    }
    
    public void addWord(String word) {
        Node cur=root;
        for(char c:word.toCharArray()){
            int idx=c-'a';
            if(cur.children[idx]==null)
               cur.children[idx]=new Node();
            cur=cur.children[idx];
        }
        cur.eow=true;
    }
    
    public boolean search(String word) {
        return search(word,0,root);
    }

    private boolean search(String word,int idx,Node root){
        if(root==null) return false;
        if(idx==word.length()) return root.eow;

        char c=word.charAt(idx);
        if(c!='.'){
            return search(word,idx+1,root.children[c-'a']);
        }

        for(int i=0;i<26;i++){
            if(search(word,idx+1,root.children[i]))
                return true;
        }
        return false;
    }
*/

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