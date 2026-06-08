class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        // ans = {2, -1, 2}
        for (int i = n * 2 - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i % n]) {
                st.pop();
            }

            ans[i % n] = st.isEmpty() ? -1 : st.peek();
            st.push(nums[i % n]);
        }

        return ans;
    }
}

/*
    int n = nums.length;
    Stack<Integer> s = new Stack<>();
    int[] res = new int[n];

    for (int i = 2 * n - 1; i >= 0; i--) {
        while (s.size() > 0 && nums[s.peek()] <= nums[i % n]) {
            s.pop();
        }

        res[i % n] = s.empty() ? -1 : nums[s.peek()];
        s.push(i % n);
    }

    return res;
*/