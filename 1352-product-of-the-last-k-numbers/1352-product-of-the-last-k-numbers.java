class ProductOfNumbers {

    private List<Integer> list;

    public ProductOfNumbers() {
        list = new ArrayList<>();
        list.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            list.clear();
            list.add(1);
        } else {
            int last = list.get(list.size() - 1);
            list.add(last * num);
        }
    }

    public int getProduct(int k) {
        if (k >= list.size())
            return 0;

        int n = list.size() - 1; // Bcz we added 1
        return list.get(n) / list.get(n - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */

// Time Complexity:
// add()      → O(1)
// getProduct() → O(1)
//
// Space Complexity: O(n)

// Maintain cumulative products so every k-element product can be obtained using one division instead of multiplying k elements.

/*
    .

🔑 Key Observation

For:

[3, 2, 5, 4]

prefix products:

[1, 3, 6, 30, 120]

For the last 2 numbers:

120 / 6 = 20

which is:

5 × 4 = 20

✨ Magic Line
return list.get(n) / list.get(n - k);

💡 Why Reset on Zero?

Suppose:

3, 2, 0, 5, 4

Any product containing the 0 is automatically 0.

So after encountering zero, we throw away the old prefix products and start fresh:

[1, 5, 20]

This also prevents division by a prefix product that belongs to a segment before the zero.

🧩 Pattern Recognition

Repeated range product queries → Prefix Product

Zero present → Reset the prefix structure at zero.

One note: for the actual LeetCode constraints, use long internally if your implementation risks intermediate multiplication overflow; the problem's required return type remains int.
*/

/*
   class ProductOfNumbers {

   private List<Integer> list;

   public ProductOfNumbers() {
       list = new ArrayList<>();
   }
   
   public void add(int num) {
       list.add(num);
   }
   
   public int getProduct(int k) {
       int product = 1;
       int len = list.size();

       for(int i = len - k; i < len; i++) {
           product *= list.get(i);
       }

       return product;
   }
}
*/