class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < landStartTime.length; i++) {
            for(int j = 0; j < waterStartTime.length; j++) {
                int landFinish = landStartTime[i] + landDuration[i];
                int waterAfterLand = Math.max(landFinish, waterStartTime[j]) + waterDuration[j];

                int waterFinish = waterStartTime[j] + waterDuration[j];
                int landAfterWater = Math.max(waterFinish, landStartTime[i]) + landDuration[i];

                ans = Math.min(ans, Math.min(waterAfterLand, landAfterWater));
            }
        }

        return ans;
    }
}