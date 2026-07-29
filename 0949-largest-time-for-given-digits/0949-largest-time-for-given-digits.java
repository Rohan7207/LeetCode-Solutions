class Solution {
    public String largestTimeFromDigits(int[] arr) {
        int n = arr.length;
        int maxTime = -1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i)
                    continue;

                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j)
                        continue;

                    for (int l = 0; l < 4; l++) {
                        if (l == i || l == j || l == k)
                            continue;

                        int hour = arr[i] * 10 + arr[j];

                        int minute = arr[k] * 10 + arr[l];

                        if (hour < 24 && minute < 60) {
                            int total = hour * 60 + minute;

                            if (total > maxTime) {
                                maxTime = total;
                            }
                        }
                    }
                }
            }
        }

        if (maxTime == -1) {
            return "";
        }

        int hrs = maxTime / 60;
        int mins = maxTime % 60;
        return String.format("%02d:%02d", hrs, mins);
    }
}