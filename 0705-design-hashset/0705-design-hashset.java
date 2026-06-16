// Problem: Design HashSet
// Link: https://leetcode.com/problems/design-hashset/
// Difficulty: Easy

// Approach:
// The problem says keys are in the range:
//     0 <= key <= 10^6
// So instead of building a real hash table,
// we can use a boolean array of size 1000001.
// Each index represents one key.
//     set[key] = true   means key is present
//     set[key] = false  means key is absent
// add(key):
//     Mark that key as present.
// remove(key):
//     Mark that key as absent.
// contains(key):
//     Return whether that key is present or not.

// Time Complexity:
//
//     add()      -> O(1)
//     remove()   -> O(1)
//     contains() -> O(1)
//
// Space Complexity:
//
//     O(1000001)
//     Since we store one boolean value for every possible key.


class MyHashSet {
    boolean[] set = new boolean[1000001];

    public MyHashSet() {
        
    }
    
    public void add(int key) {
        set[key] = true;
    }
    
    public void remove(int key) {
        set[key] = false;
    }
    
    public boolean contains(int key) {
        return set[key];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
