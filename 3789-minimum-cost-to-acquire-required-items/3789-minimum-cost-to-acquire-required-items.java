class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // costBoth {Type1, Type2} is costlier or equal to cost1 + cost2
        if (cost1 + cost2 <= costBoth) {
            return (1L * cost1 * need1) + (1L * cost2 * need2);
        }

        int both = Math.min(need1, need2);
        long total = 1L * both * costBoth;

        need1 -= both;
        need2 -= both;

        if (need1 > 0) {
            total += 1L * need1 * Math.min(cost1, costBoth);
        } else if (need2 > 0) {
            total += 1L * need2 * Math.min(cost2, costBoth);
        }

        return total;
    }
}

/*
    Ex: cost1 = 5 cost2 = 4 costBoth = 15   need1 = 2   need2 = 3

    If we take costBoth which gives both item 1 unit but its cost is more if we did choose this then 2 units = 30 and if we had taken seperate type1 unit1 = 5 and type2 unit1 = 4 => 9

    1st time = 9 cost => 1 unit of type1 and type2
    2nd time = 9 cost => 1 unit total 2 units of type 1 and type2
    3rd time = 4 cost => we need type2 3units so take only type2 of cost2 = 4, not both bcz sum < costboth
    Total cost = 22

    Second case where costBoth is used:
        Ex: cost1 = 3 cost2 = 2 costBoth = 1 need1 = 3 need2 = 2

        Type1  Type2  costUsed    Unit   totalcost
         3       2     costBoth   1 1       1
         2       1     costBoth   1 1       2
         1       0     costBoth   1 1       3
         0       0         
            (in last case we have options to go with cost1 not cost2 bcz it is zero or we can go with costBoth which increases unit of type2 and it is allowed as mentioned by question atleast)

        - In this case we tried to take costBoth as much as possible we took it min(need1, need2)

        int both = min(need1, need2) = 2
        total = both * costBoth;

        need1 -= both, need2 -= both;
        if(need1 > 0) {
            total += need1 * min(cost1, costBoth);
        } else {
            total += need2 * min(cost2, costBoth);
        }

        return total;

    //  Greedy Method, and we can also use recurion bcz we have choices cost1 + cost2 or costBoth
    //  costBoth(type1, type2) is costlier than taking type1 and type2 seperatly
     if(cost1 + cost2 <= costBoth) {
        // Then use cost1 and cost2 bcz it gives min total cost
        return (need1 * cost1 (type1)) + (need2 * cost2(type2));
     } else {
        int both = min(need1, need2);
        total = both * costBoth;

        need1 -= both, need2 -= both;
        if(need1 > 0) {
            total += need1 * min(cost1, costBoth);
        } else {
            total += need2 * min(cost2, costBoth);
        }
     }

     return total;
*/