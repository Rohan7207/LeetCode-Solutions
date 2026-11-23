// Problem: Product of the Last K Numbers
// Link: https://leetcode.com/problems/product-of-the-last-k-numbers/?envType=daily-question&envId=2026-11-23
// Difficulty: Medium

// Approach:
// 1. Maintain a list of cumulative products instead of storing individual numbers.
// 2. Initialize the list with 1 to simplify product calculations.
// 3. When adding a non-zero number:
//    a. Get the previous cumulative product.
//    b. Multiply it by the new number.
//    c. Store the result.
// 4. When adding zero:
//    a. Clear the list.
//    b. Add 1 again.
//    This resets the prefix products because any product containing zero is 0.
// 5. To calculate the product of the last k numbers:
//    a. If k >= list.size(), a zero exists in the requested range, so return 0.
//    b. Otherwise, divide the latest prefix product by the prefix product
//       immediately before the last k numbers.

// Time Complexity:
// add()       -> O(1)
// getProduct() -> O(1)
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
        if (k >= list.size()) {
            return 0;
        }

        int n = list.size() - 1; 
        return list.get(n) / list.get(n - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
