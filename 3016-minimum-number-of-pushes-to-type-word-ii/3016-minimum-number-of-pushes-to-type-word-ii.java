class Solution {
    public int minimumPushes(String word) {
        // Frequency Array to store count of each letters
        int[] freq = new int[26];

        // Count occurrences of each letter
        for(char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Sort the frequncies in descending order
        Arrays.sort(freq);
        int[] sortedFreq = new int[26];
        for(int i = 0; i < 26; i++) {
            sortedFreq[i] = freq[25 - i];
        }

        int totalPushes = 0;

        // Calculate total no.of presses
        for(int i = 0; i < 26; i++) {
            if(sortedFreq[i] == 0) break;
            totalPushes += ((i / 8) + 1 ) * sortedFreq[i];
        }

        return totalPushes;
    }
}