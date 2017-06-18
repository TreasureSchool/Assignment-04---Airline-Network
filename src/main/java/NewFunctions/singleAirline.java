package NewFunctions;

/**
 * @author Joachim
 */
public class singleAirline {

    public boolean singleAirline(String origin, String destination) {
        //airline_list;
        boolean breadth = true;
        boolean show_progress = true;

        /* for (edge e : edges) {
        if (e[i].sourceCode==origin) airline_list.add(e[i].airlineCode);
         */
        if (show_progress) {
            if (breadth) {
                /*for (a in tqdm(airline_list); {
                    if (breadthFirst(origin, destination, a)) return true;
                }*/
            } else {
                /*for (a in tqdm(airline_list); {
                    if (depthFirst(origin, destination, a)) return true;
                }*/
            }

        } else if (breadth) {
            /*for (a in tqdm(airline_list); {
                    if (breadthFirst(origin, destination, a)) return true;
                }*/
        } else {
            /*for (a in tqdm(airline_list); {
                    if (depthFirst(origin, destination, a)) return true;
                }*/
        }
        return false;
    }
}
