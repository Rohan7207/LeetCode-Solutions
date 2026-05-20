// Problem: Count Primes
// Link: https://leetcode.com/problems/count-primes/
// Difficulty: Medium

// Approach:
// Use the Sieve of Eratosthenes to count
// prime numbers less than n.
// Since all even numbers greater than 2 are
// non-prime, process only odd numbers.
// Initially assume all odd numbers below n
// are prime and maintain their count.
// Traverse odd numbers from 3 up to √n.
// For every unmarked number:
//     - Treat it as prime.
//     - Mark all its odd multiples starting
//       from i*i as non-prime.
//     - Decrease count whenever a composite
//       number is marked for the first time.
// Return the final count of primes.

// Time Complexity: O(n log log n)
// Space Complexity: O(n)


class Solution {
    public int countPrimes(int n) {
        if(n <= 2) return 0;
        
        boolean[] isNotPrime = new boolean[n];
        int count = n / 2;

        for(int i = 3; i * i < n; i += 2) {
            if(!isNotPrime[i]) {
                for(int j = i * i; j < n; j += 2 * i) {
                    if(!isNotPrime[j]) {
                        isNotPrime[j] = true;
                        count--; 
                    }
                }
            }
        }

        return count;
    }
}
