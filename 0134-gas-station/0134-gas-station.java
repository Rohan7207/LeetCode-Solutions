class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int startIndex = 0;
        int tank = 0;

        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];

            //If tank is negative reset the starting index as next and set tank as 0
            if(tank < 0){
                startIndex = i + 1;
                tank = 0;
            }
        }

        //If total gas is less total cost return -1
        if(totalGas < totalCost){
            return -1;
        }

        return startIndex;
    }
}

/*
    //Greedy Algorithm with O(n) and O(1)
    We use tank variable used to store result
    firstly tank is empty and tank=gas[0]
    Consider gas= 1 2 3 4 5 and cost = 3 4 5 1 2
    1.in=0 t=1 and cost=3  to reach index 1 we must pay 3 cost i.e. 1-3 -2
        that means we cannot reach 1
    2.in=1 t=2 and c=4 2-4 -2
    3.in=2 t=3 and c=5 3-5 -2
    4.in=3 t=4 and c=1 4-1  3 => tn=3
    5.in=4 t=3+5 and c=2 8-2  6 => tn=6
    6.in=0 t=6+1 and c=3 7-3 4 => tn=4
    7.in=1 t=4+2 and c=4 6-4 2 => tn=2
    8.in=2 t=2+3 and c=5 5-5 0 => tn=0
    When tn=0 return that index position i.e. is index(2)=3
*/