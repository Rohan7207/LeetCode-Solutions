class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        long prev = 1;

        for(int i = 1; i <= rowIndex; i++) {
            long next_val = prev * (rowIndex - i + 1) / i;
            res.add((int)next_val);
            prev = next_val;
        }

        return res;
    }
}

/*
    //O(k^2) and O(k) k = rowIndex
    List<Integer> res = new ArrayList<>();
    // Initialize the list with the first as '1'
    res.add(1);

    for(int i = 1; i <= rowIndex; i++) {
        // Step 1: Add a 1 at the end to expand the row size
        res.add(1);

        // Step 2: Update internal elements from right to left
        // We start at j = i - 1 (the element before the last 1)
        for(int j = i - 1; j > 0; j--) {
            res.set(j, res.get(j) + res.get(j - 1));
        }
    }

    return res;
*/