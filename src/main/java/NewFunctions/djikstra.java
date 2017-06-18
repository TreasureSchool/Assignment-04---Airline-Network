package NewFunctions;

/**
 * @author Joachim
 */
public class djikstra {

    public void djikstra(String origin, String destination) {
        boolean by_destination = true;        
        /*cost_table.clear()
        for (vertice v in vertices.keys) {
            if (v.equals(origin)) {
                cost_table.set(v, new cost(false, 0, v))
            } else {
                cost_table.set(v, new cost(false, Double.POSITIVE_INFINITY, none))
            }
        }*/
        if (by_destination) {
            System.out.println("Finding shortest route between " + origin + " and " + destination);
            //djikstraDistance(origin, destination);
        } else {
            System.out.println("");
            //djikstraTime(origin, destination);
        }
    }
}
