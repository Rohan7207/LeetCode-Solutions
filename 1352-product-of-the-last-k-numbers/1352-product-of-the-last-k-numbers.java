// Problem: Product of the Last K Numbers
// Link: https://leetcode.com/problems/product-of-the-last-k-numbers/
// Difficulty: Medium

// Approach:
// Use Prefix Product + Reset at Zero.
//
// 1. Maintain a list where each element stores the cumulative product
//    of all numbers added since the most recent zero.
//
// 2. Start the list with 1 as a dummy prefix product.
//
// 3. When adding a non-zero number, multiply it with the previous
//    prefix product and store the result.
//
// 4. When 0 is added, clear the list and restart with 1.
//    This is important because any product containing 0 is 0.
//
// 5. For getProduct(k), divide the total product by the prefix
//    product before those k elements:
//
//      product of last k = prefix[n] / prefix[n-k]
//
// 6. If k is larger than or equal to the number of stored elements,
//    there are not enough non-zero numbers after the latest zero,
//    so return 0.

// Time Complexity:
// add()      → O(1)
// getProduct() → O(1)
//
// Space Complexity: O(n)


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
