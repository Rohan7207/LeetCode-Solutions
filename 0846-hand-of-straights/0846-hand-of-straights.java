class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        //Greedy Algorithm O(nlogn) and O(n)
        /*We sort and store the frequency in TreeMap with unique keys and freq
        We should create group into no.of groupsize
        We take the smallest value and add to res reduce freq and if freq
        is 0 and remove it , if treeMap size is 0 return true or false
        and we can optimize like if handsize is divisble by groupsize to 0
        then go ahead or return false*/

        if (hand.length % groupSize != 0)
            return false; //If not divisible return false

        //TreeMap is used to store the frequency of elements and stores them 
        //in sorted manner
        TreeMap<Integer, Integer> cardCounts = new TreeMap<>();
        //Count each card
        for (int card : hand) {
            //Looks for cards if not present adds it if present increases freq
            cardCounts.put(card, cardCounts.getOrDefault(card, 0) + 1);
        }

        //Attempt to form groups
        while (!cardCounts.isEmpty()) {
            int first = cardCounts.firstKey(); // start with smallest card
            for (int i = 0; i < groupSize; i++) {
                int currentCard = first + i; //it helps to check consecutive no.s

                if (!cardCounts.containsKey(currentCard)) {
                    return false; //Cannot form group 
                }

                //Decrease count or remove card if count is 0
                int count = cardCounts.get(currentCard);
                if (count == 1) {
                    cardCounts.remove(currentCard);
                } else {
                    cardCounts.put(currentCard, count - 1);
                }
            }
        }

        return true;
    }
}