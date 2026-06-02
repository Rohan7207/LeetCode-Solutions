class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> res = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
        }

        dfs("JFK");

        return res;
    }

    private void dfs(String airport) {
        PriorityQueue<String> pq = graph.get(airport);

        while (pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }

        res.addFirst(airport);
    }
}


/*
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        //We use graph and dfs traversal and start from jfk
        //Use and hashmap and store in lexico order 
        //Create a map to store list of destination for each departure

        Map<String,List<String>> graph = new HashMap<>();
        for(List<String> ticket : tickets){
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }

        //Sort the destinations for each departure airport in lexical order
        for(List<String> destinations : graph.values()){
            Collections.sort(destinations);
        }

        //Start from itinerary from "JFK"
        LinkedList<String> res = new LinkedList<>();
        dfs("JFK" , graph , res);

        return res;
    }

    private void dfs(String airport, Map<String, List<String>> graph, LinkedList<String> res){
        List<String> destinations = graph.get(airport);
        while(destinations != null && !destinations.isEmpty()){
            //Remove next destination to avoid revisiting same path
            String next = destinations.remove(0);
            dfs(next, graph, res);
        }

        //Add the airport to res at beginning to build res in reverse order
        res.addFirst(airport);
    }
}
*/