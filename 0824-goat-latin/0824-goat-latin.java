class Solution {
    public String toGoatLatin(String sentence) {
        // Step 1: Split the sentence into an array of individual words
        String[] words = sentence.split(" ");
        String[] res = new String[words.length];

        String vowels = "aeiouAEIOU";
        StringBuilder suffixA = new StringBuilder("a");

        // Step 2: Loop through each word and transform it
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder sb = new StringBuilder();
            char firstChar = word.charAt(0);

            // Rule 1 & 2: Check for vowel or consonant start
            if (vowels.indexOf(firstChar) != -1) {
                sb.append(word); // Vowel
            } else {
                sb.append(word.substring(1)).append(firstChar);  //consonant
            }

            // Rule 3: Append "ma" and the index-based "a" strings
            sb.append("ma").append(suffixA);

            // Store the result directly into the output array
            res[i] = sb.toString();

            // Add an extra 'a' for the next word's suffix
            suffixA.append("a");
        }

        
        //  Manually build the final single String from the new array
        StringBuilder finalResult = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            finalResult.append(res[i]);
            
            // Add a space between words, but NOT after the very last word
            if (i < res.length - 1) {
                finalResult.append(" ");
            }
        }

        return finalResult.toString();
    }
}