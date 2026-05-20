class Solution {
    public int countPrimes(int n) {
        if(n <= 2) return 0;

        // We only care about odd numbers. 
        // index i represents the number (2*i + 1)
        boolean[] isNotPrime = new boolean[n];
        int count = n / 2; // Initial guess: all odds < n are prime (e.g., for n=10, 1,3,5,7,9)


        for(int i = 3; i * i < n; i += 2) {  //we increment i by 2 to get odd
            if(!isNotPrime[i]) {
                for(int j = i * i; j < n; j += 2 * i) {
                    if(!isNotPrime[j]) {
                        isNotPrime[j] = true;
                        count--; // Found a multiple, so it's not prime
                    }
                }
            }
        }

        return count;
    }
}

/*
// odd-only sieve - optimized approach
    j += 2 * i: Since i is odd, i + i would be even. We skip the even multiples and jump straight to the next odd multiple (e.g., if i=3, we jump from 9 to 15 , skipping 12).
    isNotPrime: Flipping the boolean logic (starting with false) is often faster in Java because arrays are initialized to false by default, saving the Arrays.fill step.
    One Pass: We decrement the count inside the loop, so we don't need a final for loop to count the true values.

    But 2 is prime but it is included with 1
    10 / 2 = 5. The numbers this "represents" are the odd numbers: 1, 3, 5, 7, 9.
    The Error: This count includes 1 (not prime) but excludes 2 (is prime).
    The Fix: Since we lose one (2) and gain one (1), they cancel each other out! The total count remains correct.
*/

/* 
    Brute Force: — Too many redundant checks.
    Sieve of Eratosthenes: — Extremely close to linear time.

    The Outer Loop (The "Prime Finder")
    This loop walks through numbers one by one to find the next prime.
    Job: Find a number that is still marked true.
    Optimization: It only needs to go from 2 up to 
    . If a number has a factor, at least one factor must be less than or equal to 
    .
    The Inner Loop (The "Eliminator")
    Once the outer loop finds a prime, the inner loop "knocks out" all of its multiples.
    Job: Mark isPrime[i * j] = false.
    Optimization: Start at i * i. For example, if you are at prime 5, you don't need to mark 10, 15, or 20 because the primes 2 and 3 already handled those! You start at 25.
        
//99ms with 65.98%   , which is nearly as fast as a single loop O(n)
    if(n == 0 || n == 1) return 0;

    boolean[] isPrime = new boolean[n];
    Arrays.fill(isPrime, true);
    int count = 0;

    for(int i = 2; i * i < n; i++) {
        if(isPrime[i]) {
            // Inner loop: jump by 'i' to find all multiples
            for(int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
    }

    for(int i = 2; i < n; i++) {
        if(isPrime[i]) {
            count++;
        }
    }

    return count;
*/