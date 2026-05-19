class Solution {
    public boolean isHappy(int n) {
      int slow = n;
        int fast = n;
        //while loop is not used here because initially slow and 
        //fast pointer will be equal only, so the loop won't run.
        do {
            //slow moving one step ahead and fast moving two steps ahead

            slow = square(slow);
            fast = square(square(fast));
        } while (slow != fast);

        //if a cycle exists, then the number is not a happy number
        //and slow will have a value other than 1

        return slow == 1;
    }
    
    public int square(int num) {
        
        int ans = 0;
        
        while(num > 0) {
            int remainder = num % 10;
            ans += remainder * remainder;
            num /= 10;
        }
        
        return ans;
    }
}

/*
    We use hashset and store all possible values of n if n is
    present in hashset it means cycle
    O(n) and O(n)

     Set<Integer> seenNum = new HashSet<>();

        while(n != 1 && !seenNum.contains(n)) {
            seenNum.add(n);
            n  = getSumOfSquares(n);
        }

        return n == 1;
    }


    private int getSumOfSquares(int n) {
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        return sum;
    }
*/