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

/*
    public String destCity(List<List<String>> paths) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> cities = new HashSet<>();

        for(int i = 0; i < paths.size(); i++) {
            String origin = paths.get(i).get(0);
            String des = paths.get(i).get(1);

            map.put(origin, map.getOrDefault(origin, 0) + 1);
            cities.add(origin);
            cities.add(des);
        }

        int len = cities.size();

        for(String city : cities) {
            if(!map.containsKey(city)) {
                return city;
            }
        }

        return "";
    }
*/