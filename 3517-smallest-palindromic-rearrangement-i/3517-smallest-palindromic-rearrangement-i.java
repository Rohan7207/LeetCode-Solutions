class Solution {
    public String smallestPalindrome(String s) {
        // Step 1: Count frequency of every character
        int[] freq = new int[26];

        for(char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Step 2: Build the left half
        StringBuilder left = new StringBuilder();

        // Step 3: Store the middle character (if any)
        char middle = '\0';

        // Step 4: Traverse characters from 'a' to 'z'
        for(int i = 0; i < 26; i++) {
            // If frequency is odd, remember the middle character
            if(freq[i] % 2 != 0) {
                middle = (char) (i + 'a');
            }

            // Add freq[i] / 2 copies to the left half
            char ch = (char) (i + 'a');

            for(int count = 0; count < freq[i] / 2; count++) {
                left.append(ch);
            }
        }

        // Step 5: Build the right half
        StringBuilder right = new StringBuilder(left).reverse();

        if(middle == '\0') {
            return left.toString() + right.toString();
        }

        return left.toString() + middle + right.toString();
    }
}