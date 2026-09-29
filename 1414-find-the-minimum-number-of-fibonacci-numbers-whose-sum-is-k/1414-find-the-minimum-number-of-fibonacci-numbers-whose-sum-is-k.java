class Solution {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);

        while(true) {
            int next = fib.get(fib.size() - 1) + fib.get(fib.size() - 2);

            if(next > k) break;

            fib.add(next);
        }
        
        int ans = 0;
        int len = fib.size();
        
        for(int i = len - 1; i >= 0; i--) {
            if(k == 0) {
                break;
            }

            if(fib.get(i) <= k) {
                ans++;
                k -= fib.get(i);
            }
        }

        return ans;
    }
}