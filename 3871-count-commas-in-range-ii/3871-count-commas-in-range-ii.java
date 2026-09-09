class Solution {
    public long countCommas(long n) {
        long res = 0;
        long lower = 1000;
        long commas = 1;

        while (lower <= n) {
            long upper = (lower * 1000 - 1);

            if (upper > n) {
                upper = n;
            }

            long countOfCommas = upper - lower + 1;
            res += (countOfCommas * commas);

            lower *= 1000;
            commas += 1;
        }

        return res;
    }
}

// 1,000,000,000,000,000
// 1,000,000,000
// 1,000,000

/*
    lower   upper       commas
    10^3    10^6 - 1      1
    10^6    10^9 - 1      2
    10^9    10^12 - 1     3
    10^12   10^15 - 1     4

    while(lower <= n) {
        upper = (lower * 1000 - 1)
        if(upper > n) upper = n;

        countOfCommas = upper- lower + 1
        res += (countOfCommas * commas);

        lower *= 1000;
        commas += 1;
    }
*/