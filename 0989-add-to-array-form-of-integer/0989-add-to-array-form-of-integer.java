class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        int i = num.length - 1;

        // Loop as long as there are digits left in the array OR k has a value to carry over
        while (i >= 0 || k > 0) {
            if (i >= 0) {
                k += num[i]; // Add the current array digit to k
                i--;
            }

            res.add(k % 10); // Add the last digit of the running sum to the result
            k /= 10; // Carry over the remaining value
        }

        // Since we added digits from right to left, the list is backwards. Reverse it.
        Collections.reverse(res);
        return res;
    }
}