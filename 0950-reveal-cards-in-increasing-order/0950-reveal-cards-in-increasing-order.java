class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            if (!dq.isEmpty()) {
                dq.addFirst(dq.removeLast());
            }

            dq.addFirst(deck[i]);
        }

        int[] ans = new int[n];
        int idx = 0;

        for (int num : dq) {
            ans[idx] = num;
            idx++;
        }

        return ans;
    }
}