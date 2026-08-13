class Solution {

    // Original string as a character array
    private char[] sArr;

    // pre[u]  = longest same-character prefix of segment u
    // suf[u]  = longest same-character suffix of segment u
    // maxLen[u] = longest same-character substring anywhere in segment u
    private int[] pre, suf, maxLen;

    // First and last character of each segment
    private char[] leftChar, rightChar;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        sArr = s.toCharArray();

        // Segment tree needs at most 4*n nodes
        pre = new int[4 * n];
        suf = new int[4 * n];
        maxLen = new int[4 * n];
        leftChar = new char[4 * n];
        rightChar = new char[4 * n];

        // Build the segment tree
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        // Process every update
        for (int i = 0; i < k; i++) {
            // Update the character at the given index
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));

            // Root represents the complete string,
            // so maxLen[1] is the answer
            ans[i] = maxLen[1];
        }

        return ans;
    }

    // Combines information from the left and right child
    // to calculate information for the current node.
    private void pushUp(int u, int l, int r) {
        int mid = (l + r) >> 1;

        // Number of elements in left and right segments
        int leftLen = mid - l + 1;
        int rightLen = r - mid;

        // Children of node u
        int left = u << 1;
        int right = (u << 1) | 1;

        // First character of parent comes from left child
        leftChar[u] = leftChar[left];

        // Last character of parent comes from right child
        rightChar[u] = rightChar[right];

        // Calculate prefix
        // Normally, prefix comes completely from left child
        pre[u] = pre[left];

        // If the entire left segment has the same character
        // and the boundary characters are equal,
        // the prefix can continue into the right segment.
        if (pre[left] == leftLen && rightChar[left] == leftChar[right]) {
            pre[u] = pre[left] + pre[right];
        }

        // Calculate suffix
        // Normally, suffix comes completely from right child
        suf[u] = suf[right];

        // If the entire right segment has the same character
        // and the boundary characters are equal,
        // the suffix can continue into the left segment.
        if (pre[right] == rightLen && rightChar[left] == leftChar[right]) {
            suf[u] = suf[right] + suf[left];
        }

        // Calculate maximum repeating substring
        // First consider the best answer completely
        // inside either child.
        maxLen[u] = Math.max(maxLen[left], maxLen[right]);

        // If boundary characters are equal,
        // a repeating substring can cross the boundary.
        if (rightChar[left] == leftChar[right]) {
            maxLen[u] = Math.max(maxLen[u], suf[left] + pre[right]);
        }
    }

    // Build the segment tree
    private void build(int u, int l, int r) {
        // Leaf node represents one character
        if (l == r) {
            pre[u] = 1;
            suf[u] = 1;
            maxLen[u] = 1;

            leftChar[u] = sArr[l];
            rightChar[u] = sArr[l];

            return;
        }

        int mid = (l + r) >> 1;

        // Build left child
        build(u << 1, l, mid);

        // Build right child
        build((u << 1) | 1, mid + 1, r);

        // Combine both children
        pushUp(u, l, r);
    }

    // Update the character at position pos
    private void update(int u, int l, int r, int pos, char ch) {
        // Reached the character that needs to be changed
        if (l == r) {
            leftChar[u] = ch;
            rightChar[u] = ch;
            return;
        }

        int mid = (l + r) >> 1;

        // Position lies in the left child
        if (pos <= mid) {
            update(u << 1, l, mid, pos, ch);
        }

        // Position lies in the right child
        else {
            update((u << 1) | 1, mid + 1, r, pos, ch);
        }

        // After updating the child,
        // recalculate the current node.
        pushUp(u, l, r);
    }
}