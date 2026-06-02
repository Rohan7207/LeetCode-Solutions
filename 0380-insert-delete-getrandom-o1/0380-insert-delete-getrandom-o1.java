// Problem: Insert Delete GetRandom O(1)
// Link: https://leetcode.com/problems/insert-delete-getrandom-o1/
// Difficulty: Medium

// Approach:
// Use HashMap + ArrayList.
//
// HashMap stores:
//     value -> index in list
//
// ArrayList stores:
//     actual values
//
// insert():
//     - If value already exists, return false.
//     - Add value to list.
//     - Store its index in map.
//
// remove():
//     - If value does not exist, return false.
//     - Get its index.
//     - Swap it with last element.
//     - Update last element index in map.
//     - Remove last element from list.
//     - Remove value from map.
//
// getRandom():
//     - Generate random index.
//     - Return value from list.

// Time Complexity: O(1)
// Space Complexity: O(n)


class RandomizedSet {
    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random rand;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int index = map.get(val);
        int lastElement = list.get(list.size() - 1);

        list.set(index, lastElement);
        map.put(lastElement, index);

        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }

    public int getRandom() {
        int randIdx = rand.nextInt(list.size());
        return list.get(randIdx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
