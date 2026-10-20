class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] sortedArray = new int[n];
        for (int i = 0; i < n; i++) {
            sortedArray[i] = nums[i];
        }

        Arrays.sort(sortedArray); // O(nlogn)
        int sum = 0;

        // No.of divisors of n, let be m then this loop would run m times and inner loop runs n / k times bcz we skip k size blocks, overall O(n / k * k) => O(n), therefore overall O(m * n)
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            //  Runs n / k times bcz we skip k size blocks and inside loop KMP search takes linear time 
            // O(n) and it is working on k so KMP would take O(k) then overall O(n / k * k) => O(n)
            // Check all k block sizes
            boolean validK = true;
            for (int start = 0; start < n; start += k) {
                int[] curr = new int[k];
                int[] target = new int[k];

                for (int j = start; j < start + k; j++) {
                    curr[j - start] = nums[j];
                    target[j - start] = sortedArray[j];
                }

                if (!isRotationPossible(curr, target)) {
                    validK = false;
                    break;
                }
            }

            if (validK) {
                sum += k;
            }
        }

        return sum; // O(nlogn + m * n) and O(n + k)
    }

    private boolean isRotationPossible(int[] curr, int[] target) {
        int len = target.length;
        int[] text = new int[2 * len];

        for (int i = 0; i < len; i++) {
            text[i] = target[i];
        }

        for (int i = len; i < text.length; i++) {
            text[i] = target[i - len];
        }

        return KMPSearch(curr, text); // O(k)
    }

    // Function to compute LPS(Longest Proper Prefix which is also Suffix) array
    private void computeLPS(int[] pattern, int[] lps) {
        int m = pattern.length;
        int len = 0; // Length of previous longest prefix nad suffix

        lps[0] = 0; // Bcz there is no proper suffix and prefix of patten[0..0]

        int i = 1;
        while (i < m) {
            if (pattern[i] == pattern[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // You can also write, len = len - 1;
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    private boolean KMPSearch(int[] pattern, int[] text) {
        int n = text.length;
        int m = pattern.length;

        // Create an LPS array to store longest proper prefix which is also a suffix 
        // lps[i] = the longest proper prefix of curr[0..i] which is also a suffix of curr[0..i].
        int[] lps = new int[m];
        computeLPS(pattern, lps);

        int i = 0; // Index for text
        int j = 0; // Index for pattern

        while (i < n) {
            if (pattern[j] == text[i]) {
                i++;
                j++;
            }

            if (j == m) {
                // Patten found at index i - j + 1 (If you have to return 1 based on index that's why added + 1)
                return true; // Found a match
            } else if (i < n && pattern[j] != text[i]) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return false;
    }
}

/*
    The questions asks to find sum of all possible sortable integers k where k is called sortable if k divides n means k are values of divisors of n and then we should partition given array into subarray of length k and we should independentlu rotate k length subarray of array to for sorted array if it is possible then add it to sum

    nums = {7, 6, 5, 9, 3, 8}
    k = 1, k = 2, k = 3, k = 6

    For k = 1,
      7   6   5   9  3  8   since how much rotate a single integer it gives same subarray and if array was already sorted then we would add k = 1 to sum
    
    For k = 2,
     7  6    5  9     3  8   , we can't get sorted array and we should rotate only subarray part

    For ex:  {3, 1, 2}  
     k = 2,  => {3, 1, 2}  -> {2, 3, 1}  -> {1, 2, 3} (sorted add k to sum)

    Ex:   nums = {6, 5, 8, 7}   n = 4,  k = 1, 2, 4
    Brute force would be like store all possible subarray with all possible rotation and again we should check one subarray rotation with another whether it gives sorted array or not that would be super brute force and it definetly leads to TLE

        nums = {6, 5, 8, 7}   n = 4,  k = 1, 2, 4

        For k = 2, the goal is after rotation we should got sorted array right lets see how sorted array looks,
        sorted = {5, 6, 7, 8}, now lets check first block i.e. 6, 5 since we should only rotate 6 and 5 only so other elements will not come in picture and now we should check that whether those particular k size elements is present in sorted array or not, like

        curr =  {6, 5}  -> After n rotations ->   target block is = {5, 6}  => We should get it efficently

    - How do I check that rotation of [6, 5] can become [5, 6]
    * This is standard problem to check rotation become target by using a property

            [6, 5]                  [5, 6]
    Property: To check first append the target array back to it like [5, 6, 5, 6] and then check whether current k subarray is present as subarray in appended target array [5, 6, 5, 6], which is present in pos 1, 2 which gurantees us that after n rotations [6, 5] will become [5, 6]

    Why it works? 
    - It works bcz by appending same array back to it it generates all possible rotations of its elements like for [5, 6] => [5, 6, 5, 6], first there where only two rotations 5, 6 and 6, 5 and in appended 5, 6  6, 5  5, 6  though we get same but for bigger it would give all possible rotations. 
    - To check whether curr k subarray after rotation become target subarrauy then we simply check whether curr is present as subarray in appended subarray.
    - Though by looking it we can say that checking curr as subarray of target would be time taking and lead to O(n ^ 2), but target would be larger and k would be smaller so we can effiecintly check wheteher is subarray or not by using KMP.

    Story Point:
    1. Sorted array of nums
    2. for(int k = 1; k <= n; k++) {
            if(n % k != 0) continue;

            // Check k is sortable or not, start += k to skip curr block
            validK = true;
            for(start = 0; start < n; start += k) {
                // curr block start...k - 1
                curr = int[k] from nums
                target = int[k] from sorted 

                if(!isRotationPossible(curr, target)) {
                    validK = false;   // mark as false bcz k does'nt form valid sortable when one block fails
                    break;  // bcz if one block doesn't form target block then terminate there itself there is no point in checking other blocks
                }
            }

            if(validK) {
                sum += k;
            }
       }

       return sum;


    bool isRotationPossible(curr, target) {
        return KMP(curr, target + target);
        // where in KMP curr would be pattern and target + target would be the target for KMP 
    }
*/