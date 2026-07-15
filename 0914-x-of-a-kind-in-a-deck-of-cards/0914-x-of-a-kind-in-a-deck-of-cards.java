class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int n = deck.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int card : deck) {
            freqMap.put(card, freqMap.getOrDefault(card, 0) + 1);
        }

        int gcdValue = 0;
        for(int freq : freqMap.values()) {
            gcdValue = gcd(gcdValue, freq);
        }

        return gcdValue > 1;
    }

    private int gcd(int a, int b) {
        while(b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}