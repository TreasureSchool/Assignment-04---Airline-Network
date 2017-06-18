package NewFunctions;

/**
 * @author Joachim
 */
public class breadthFirst {
    public boolean breadthFirst(String origin, String destination, String airline, flexibleArray<String> visited, flexibleArray<String> queue) {
        visited.add(origin);
        if (origin.equals(destination)) {
            return true;
        }
        /*for (edge e : edges) {
            if(e.source_code.equals(origin) && e.airline_code.equals(airline) {
                if(!visited.contains(e.destination_code) && !queue.contains(e.destination_code) {
                    queue.add(e.destination_code);
                }
            }
        }*/
        
        if(queue.size() < 1) {
            return false;
        }
        
        String next_vertex = queue.get(0);
        flexibleArray<String> NewQueue = new flexibleArray();
        for (int v = 1; 0 < queue.size(); v++) {
            NewQueue.add(queue.get(v));
        }
        return breadthFirst(next_vertex, destination, airline, visited, NewQueue);
    }
}
