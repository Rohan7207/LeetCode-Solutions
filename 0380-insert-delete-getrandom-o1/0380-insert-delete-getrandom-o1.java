
class RandomizedSet {
    //We use hashmap with key as item and value as index with O(1)

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

        //swap the element and set it
        list.set(index, lastElement);
        map.put(lastElement, index);

        /// remove last element
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

 /*
    The challenge is:

Remove an element in O(1)

Removing from the middle of an ArrayList normally takes:

O(n)

because elements have to shift.

So we use a trick:

Swap with last element
Then remove last element
Example

Suppose:

list = [10,20,30,40]
map  = {10=0,20=1,30=2,40=3}

Remove:

30
Step 1
int index = map.get(val);
index = 2

because:

30 is at index 2
Step 2
int lastElement = list.get(list.size() - 1);
lastElement = 40
Step 3
list.set(index, lastElement);

Replace:

list[2] = 40

List becomes:

[10,20,40,40]

Notice:

30 is overwritten
Step 4
map.put(lastElement, index);

Update map:

40 moved from index 3
to index 2

Map becomes:

{10=0,20=1,30=2,40=2}
Step 5
list.remove(list.size() - 1);

Remove last element:

[10,20,40]

Now duplicate 40 disappears.

Step 6
map.remove(val);

Remove:

30

Map becomes:

{10=0,20=1,40=2}
Final State
list = [10,20,40]

map = {
 10 -> 0
 20 -> 1
 40 -> 2
}
Why Not Direct Remove?
list.remove(index);

For:

[10,20,30,40]

removing index 2 requires:

40 shifting left

Cost:

O(n)
Why This Works?

Because:

Removing last element

from ArrayList is:

O(1)

and

HashMap operations

are:

O(1)

So total:

O(1)
Special Case

Suppose removing last element itself:

list = [10,20,30]
remove(30)

Then:

index = 2
lastElement = 30

The swap does nothing:

30 ↔ 30

and removal still works correctly.
 */