package NewFunctions;

/**
 * @author Joachim
 */
public class djikstraTime {
    String original = "";
    public void djikstraTime(String origin, String destination) {
        flexibleArray<String> distanceQueue = new flexibleArray(); 
        int transit = 60;
        //cost_table[origin]['visited']=true;
        if (original.equals("")) {
            original = origin;
        }
        if (origin.equals(destination)) {
            System.out.println("Found destination");
            //backtrack_cost_table.put(origin, destination);
            return;
        }
        /*for (edge e in edges) {
            if (e.source_code.equals(origin) {
                if(cost_table.get(e.destination_code).visited == false && !distanceQueue.contains(e.destination_code)) {
                    distanceQueue.put(e.destination_code);
                }
                if ((float) e.time + cost_table.get(origin).cost < cost_table.get(e.destination_code).cost) {
                    cost_table.get(e.destination_code).cost = (float) e.time + cost_table.get(origin).cost + 1;
                    cost_table.get(e.destination_code).parent = origin;
                }
            }
        }
        */
        
        if (distanceQueue.size() < 1) {
            System.out.println("End of queue, airport is not connected");
            return;
        }
        
        String next_vertex = distanceQueue.get(0);
        flexibleArray<String> NewQueue = new flexibleArray();
        for (int v = 1; 0 < distanceQueue.size(); v++) {
            NewQueue.add(distanceQueue.get(v));
        }
        djikstraTime(next_vertex, destination, original, NewQueue);
    }
    
    public void djikstraTime(String origin, String destination, String original, flexibleArray<String> newQueue) {
        int transit = 60;
        //cost_table[origin]['visited']=true;
        if (original.equals("")) {
            original = origin;
        }
        if (origin.equals(destination)) {
            System.out.println("Found destination");
            //backtrack_cost_table.put(origin, destination);
            return;
        }
        /*for (edge e in edges) {
            if (e.source_code.equals(origin) {
                if(cost_table.get(e.destination_code).visited == false && !newQueue.contains(e.destination_code)) {
                    newQueue.put(e.destination_code);
                }
                if ((float) e.time + cost_table.get(origin).cost < cost_table.get(e.destination_code).cost) {
                    cost_table.get(e.destination_code).cost = (float) e.time + cost_table.get(origin).cost + 1;
                    cost_table.get(e.destination_code).parent = origin;
                }
            }
        }
        */
        
        if (newQueue.size() < 1) {
            System.out.println("End of queue, airport is not connected");
            return;
        }
        
        String next_vertex = newQueue.get(0);
        flexibleArray<String> NewQueue = new flexibleArray();
        for (int v = 1; 0 < newQueue.size(); v++) {
            NewQueue.add(newQueue.get(v));
        }
        djikstraTime(next_vertex, destination, original, NewQueue);

    }
}
