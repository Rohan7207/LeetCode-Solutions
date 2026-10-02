// Problem: Destination City
// Link: https://leetcode.com/problems/destination-city/
// Difficulty: Easy

// Approach:
// 1. Every path has two cities:
//    [source, destination]
//
// 2. The destination city is special because it is the final city,
//    meaning it never appears as a starting/source city.
//
// 3. Store every starting city in a HashSet.
//
// 4. Traverse all destinations.
//    If a destination is NOT present in the set of starting cities,
//    that city must be the destination city.
//
// 5. Return it immediately.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> startingCities = new HashSet<>();

        for (List<String> path : paths) {
            startingCities.add(path.get(0));
        }

        for (List<String> path : paths) {
            String des = path.get(1);

            if (!startingCities.contains(des)) {
                return des;
            }
        }

        return "";
    }
}
