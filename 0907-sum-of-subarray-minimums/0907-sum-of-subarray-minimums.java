class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int MOD = 1_000_000_007;

        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> s = new Stack<>();

        // Find Previous Smaller Element (Left Choices)
        for(int i = 0; i < n; i++) {
            while(!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }

            left[i] = s.isEmpty() ? -1 : s.peek();
            s.push(i);
        }

        s.clear();

        // Find Next Smaller Element (Right Choices)
        for(int i = n - 1; i >= 0; i--) {
            while(!s.isEmpty() && arr[s.peek()] > arr[i]) {
                s.pop();
            }

            right[i] = s.isEmpty() ? n : s.peek();
            s.push(i);
        }

        // Calculate contribution of every element : Contribution = arr[i] × (i-PSE) × (NSE-i)
        long ans = 0;

        for(int i = 0; i < n; i++) {
            long contribution = (long) arr[i] * (i - left[i]) * (right[i] - i);

            ans = (ans + contribution) % MOD;
        }

        return (int) ans;
    }
}