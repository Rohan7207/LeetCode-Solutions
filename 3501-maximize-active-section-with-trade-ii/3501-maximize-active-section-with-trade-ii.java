// Problem: Maximize Active Section With Trade II
// Link: https://leetcode.com/problems/maximize-active-section-with-trade-ii/?envType=daily-question&envId=2026-07-23
// Difficulty: Hard

// Approach:
// Since there are many queries, scanning the substring for every query
// would take O(n × q), which is too slow. Instead, preprocess the string
// once and answer each query efficiently.
//
// Step 1: Identify all maximal contiguous blocks of '0's.
// Store:
// • zs[i] -> starting index of the i-th zero block.
// • ze[i] -> ending index of the i-th zero block.
//
// Example:
// s = "0011100111000"
//
// Zero blocks:
// 00   00   000
//
// zs = [0, 5, 10]
// ze = [1, 6, 12]
//
// Step 2: Build a "Valley" array.
//
// Every removable block of '1's lies between two adjacent zero blocks.
// If that '1' block is removed, the two neighboring zero blocks merge.
//
// Therefore, for every adjacent pair of zero blocks compute:
//
// valley[i] = length(left zero block) + length(right zero block)
//
// Store these values in array V.
//
// Example:
//
// 00 111 0000
//
// left length  = 2
// right length = 4
//
// valley = 6
//
// Each valley represents the gain obtained by removing the
// '1' block between those two zero blocks.
//
// Step 3: Build a Sparse Table on V.
//
// Each query asks for the maximum obtainable valley inside
// a substring.
//
// Since there are many queries, preprocess V into a Sparse
// Table so that range maximum queries can be answered in O(1).
//
// Sparse Table preprocessing:
//
// Time : O(m log m)
// where m = number of valleys.
//
// Step 4: Process each query.
//
// For every query [l, r]:
//
// • Locate the first valley whose left zero block overlaps
//   the substring.
//
// • Locate the last valley whose right zero block overlaps
//   the substring.
//
// Binary Search is used for this.
//
// Step 5: Handle boundary valleys.
//
// The first and last valleys may only be partially contained
// inside the query.
//
// Their gain is adjusted ("clipped") by subtracting the zero
// portions lying outside the substring.
//
// Interior valleys are completely inside the substring,
// so their values can be obtained directly from the Sparse Table.
//
// Step 6:
//
// The best gain is:
//
// max(
//     first clipped valley,
//     last clipped valley,
//     maximum interior valley
// )
//
// Add this gain to the original number of active sections ('1's).

// Time Complexity:
//
// Preprocessing:
// • Find zero blocks      -> O(n)
// • Build valley array    -> O(n)
// • Sparse Table          -> O(n log n)
//
// Per Query:
// • Two Binary Searches   -> O(log n)
// • RMQ using Sparse Table-> O(1)
//
// Overall:
//
// O(n log n + q log n)
//
// Space Complexity:
//
// O(n log n)
// (Sparse Table + helper arrays)


import java.util.regex.*;

class Solution {
    private int[] zs, ze, V;
    private int nblocks;
    private List<int[]> sparse;

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int ones = (int) s.chars().filter(c -> c == '1').count();

        // maximal zero-blocks (inclusive ends), split into starts / ends
        List<Integer> zsL = new ArrayList<>(), zeL = new ArrayList<>();
        Matcher mo = Pattern.compile("0+").matcher(s);
        while (mo.find()) {
            zsL.add(mo.start());
            zeL.add(mo.end() - 1);
        }
        zs = zsL.stream().mapToInt(Integer::intValue).toArray();
        ze = zeL.stream().mapToInt(Integer::intValue).toArray();
        nblocks = zs.length;

        // valley j: full value = sum of the two adjacent block lengths
        V = IntStream.range(0, nblocks - 1)
                .map(j -> (ze[j] - zs[j] + 1) + (ze[j + 1] - zs[j + 1] + 1))
                .toArray();

        // sparse table for range-max over V
        int nv = V.length;
        sparse = new ArrayList<>();
        sparse.add(V);
        for (int half = 1; half * 2 <= nv; half *= 2) {
            int[] prev = sparse.get(sparse.size() - 1);
            int[] next = new int[prev.length - half];
            for (int i = 0; i < next.length; i++)
                next[i] = Math.max(prev[i], prev[i + half]);
            sparse.add(next);
        }

        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] q : queries)
            ans.add(ones + gain(q[0], q[1]));
        return ans;
    }

    private int rmq(int lo, int hi) { // inclusive max over V[lo..hi]
        int t = 31 - Integer.numberOfLeadingZeros(hi - lo + 1);
        return Math.max(sparse.get(t)[lo], sparse.get(t)[hi - (1 << t) + 1]);
    }

    private int clip(int j, int l, int r) { // valley j's gain, clipped to [l, r]
        return V[j] - Math.max(0, l - zs[j]) - Math.max(0, ze[j + 1] - r);
    }

    private int gain(int l, int r) {
        if (nblocks < 2)
            return 0;
        int ja = lowerBound(ze, l); // first usable valley: left block ends >= l
        int jb = upperBound(zs, r) - 2; // last  usable valley: right block starts <= r
        if (ja > jb)
            return 0;
        return Math.max(Math.max(clip(ja, l, r), clip(jb, l, r)),
                jb - ja >= 2 ? rmq(ja + 1, jb - 1) : 0);
    }

    // bisect_left / bisect_right equivalents
    private static int lowerBound(int[] a, int x) {
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] < x)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }

    private static int upperBound(int[] a, int x) {
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] <= x)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }
}
