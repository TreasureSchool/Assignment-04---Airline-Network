package Main;

import NewFunctions.Stopwatch;
import NewGraphs.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Joachim
 */
public class Main {

    static ArrayList<Edge> edges = new ArrayList<>();
    static ArrayList<Vertice> vertices = new ArrayList<>();
    static HashMap<Vertice, Cost> cost_table = new HashMap<>();
    static ArrayList<String> all_airlines = new ArrayList();
    static Vertice tmpVertice;
    static Edge tmpEdge;
    static Vertice tmpVertice2;
    static Edge tmpPath = new Edge();
    static ArrayList<String> visited = new ArrayList<>();
    static ArrayList<String> queue = new ArrayList<>();
    static ArrayList<Edge> a_spanningTree = new ArrayList();
    static String flyselskab = "AA";
    static int repetitions = 0;

    public static void main(String args[]) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src\\main\\java\\Supplementary\\airports.txt"), Charset.forName("UNICODE"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                tmpVertice = new Vertice(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));
                vertices.add(tmpVertice);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src\\main\\java\\Supplementary\\routes.txt"), Charset.forName("UNICODE"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                tmpEdge = new Edge(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
                edges.add(tmpEdge);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        for (int e = 0; e < edges.size(); e++) {
            all_airlines.add(edges.get(e).getAIRLINE_CODE());
        }
        //compareBFSAndDFS(10);
        //djikstra("CPH", "HNL", true);
        //djikstra("CPH", "HNL", false);
        findWidestCoverage();
    }

    public static void singleAirline(String origin, String destination, boolean breadth) {
        ArrayList<String> airline_list = new ArrayList();

        for (int e = 0; e < edges.size(); e++) {
            if (edges.get(e).getSOURCE_CODE().equals(origin)) {
                airline_list.add(edges.get(e).getAIRLINE_CODE());
            }
        }

        if (breadth) {
            for (int a = 0; a < airline_list.size(); a++) {
                if (breadthFirst(origin, destination, airline_list.get(a), visited, queue)) {
                    return;
                }
            }
        } else {
            for (int a = 0; a < airline_list.size(); a++) {
                if (depthFirst(origin, destination, airline_list.get(a), visited, queue)) {
                    return;
                }
            }
        }
    }

    public static boolean breadthFirst(String origin, String destination, String airline, ArrayList<String> visited, ArrayList<String> queue) {
        boolean run = true;
        while (run) {

            visited.add(origin);
            if (origin.equals(destination)) {
                return true;
            }

            for (int e = 0; e < edges.size(); e++) {
                if (edges.get(e).getSOURCE_CODE().equals(origin) && edges.get(e).getAIRLINE_CODE().equals(airline)) {
                    if (!visited.contains(edges.get(e).getDESTINATION_CODE()) && !queue.contains(edges.get(e).getDESTINATION_CODE())) {
                        queue.add(edges.get(e).getDESTINATION_CODE());
                    }
                }
            }
            if (queue.size() < 1) {
                return false;
            }

            String next_vertex = queue.get(0);
            queue.remove(0);
            origin = next_vertex;
        }
        return false;
    }

    public static boolean depthFirst(String origin, String destination, String airline, ArrayList<String> visited, ArrayList<String> queue) {
        boolean run = true;
        while (run) {
            visited.add(origin);
            if (origin.equals(destination)) {
                return true;
            }

            for (int e = 0; e < edges.size(); e++) {
                if (edges.get(e).getSOURCE_CODE().equals(origin) && edges.get(e).getAIRLINE_CODE().equals(airline)) {
                    if (!visited.contains(edges.get(e).getDESTINATION_CODE()) && !queue.contains(edges.get(e).getDESTINATION_CODE())) {
                        queue.add(edges.get(e).getDESTINATION_CODE());
                    }
                }
            }

            if (queue.size() < 1) {
                return false;
            }

            String next_vertex = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
            origin = next_vertex;
        }
        return false;
    }

    public static void djikstra(String origin, String destination, boolean by_destination) {
        cost_table.clear();
        for (int v = 0; v < vertices.size(); v++) {
            tmpVertice = vertices.get(v);
            Cost tmpCost;
            if (tmpVertice.getCode().equals(origin)) {
                tmpCost = new Cost(false, 0, tmpVertice);
                cost_table.put(tmpVertice, tmpCost);
            } else {
                tmpCost = new Cost(false, Double.POSITIVE_INFINITY, null);
                cost_table.put(tmpVertice, tmpCost);
            }
        }
        if (by_destination) {
            System.out.println("Finding shortest route between " + origin + " and " + destination);
            djikstraDistance(origin, destination);
        } else {
            System.out.println("Finding quickest route between " + origin + " and " + destination);
            djikstraTime(origin, destination);
        }
    }

    public static void djikstraDistance(String origin, String destination) {
        boolean action = true;
        String original = "";
        ArrayList<String> distanceQueue = new ArrayList<>();
        while (action) {
            for (int v = 0; v < vertices.size(); v++) {
                if (vertices.get(v).getCode().equals(origin)) {
                    tmpVertice = vertices.get(v);
                }
            }
            Cost tmpCost = cost_table.get(tmpVertice);
            tmpCost.setVisited(true);
            cost_table.replace(tmpVertice, tmpCost);

            if (original.equals("")) {
                original = origin;
            }
            if (origin.equals(destination)) {
                System.out.println("Found destination");
                backtrackCostTable(original, destination, false);
                return;
            }
            for (int e = 0; e < edges.size(); e++) {
                if (edges.get(e).getSOURCE_CODE().equals(origin)) {
                    for (int v = 0; v < vertices.size(); v++) {
                        if (vertices.get(v).getCode().equals(edges.get(e).getDESTINATION_CODE())) {
                            tmpVertice2 = vertices.get(v);
                        }
                    }
                    if (!cost_table.get(tmpVertice2).isVisited() && !distanceQueue.contains(edges.get(e).getDESTINATION_CODE())) {
                        distanceQueue.add(edges.get(e).getDESTINATION_CODE());
                    }
                    if ((float) edges.get(e).getDISTANCE() + cost_table.get(tmpVertice).getCost() < cost_table.get(tmpVertice2).getCost()) {
                        cost_table.get(tmpVertice2).setCost((float) edges.get(e).getDISTANCE() + cost_table.get(tmpVertice).getCost());
                        cost_table.get(tmpVertice2).setParent(tmpVertice);
                    }
                }
            }

            if (distanceQueue.size() < 1) {
                System.out.println("End of queue, airport is not connected");
                return;
            }

            String next_vertex = distanceQueue.get(0);
            distanceQueue.remove(0);
            origin = next_vertex;
        }
        //djikstraDistance(next_vertex, destination, original, NewQueue);
    }

    public static void djikstraTime(String origin, String destination) {
        boolean action = true;
        String original = "";
        ArrayList<String> timeQueue = new ArrayList<>();
        while (action) {
            for (int v = 0; v < vertices.size(); v++) {
                if (vertices.get(v).getCode().equals(origin)) {
                    tmpVertice = vertices.get(v);
                }
            }
            Cost tmpCost = cost_table.get(tmpVertice);
            tmpCost.setVisited(true);
            cost_table.replace(tmpVertice, tmpCost);

            if (original.equals("")) {
                original = origin;
            }
            if (origin.equals(destination)) {
                System.out.println("Found destination");
                backtrackCostTable(original, destination, true);
                return;
            }
            for (int e = 0; e < edges.size(); e++) {
                if (edges.get(e).getSOURCE_CODE().equals(origin)) {
                    for (int v = 0; v < vertices.size(); v++) {
                        if (vertices.get(v).getCode().equals(edges.get(e).getDESTINATION_CODE())) {
                            tmpVertice2 = vertices.get(v);
                        }
                    }
                    if (!cost_table.get(tmpVertice2).isVisited() && !timeQueue.contains(edges.get(e).getDESTINATION_CODE())) {
                        timeQueue.add(edges.get(e).getDESTINATION_CODE());
                    }
                    if ((float) edges.get(e).getTIME() + cost_table.get(tmpVertice).getCost() < cost_table.get(tmpVertice2).getCost()) {
                        cost_table.get(tmpVertice2).setCost((float) edges.get(e).getTIME() + cost_table.get(tmpVertice).getCost());
                        cost_table.get(tmpVertice2).setParent(tmpVertice);
                    }
                }
            }
            if (timeQueue.size() < 1) {
                System.out.println("End of queue, airport is not connected");
                return;
            }

            String next_vertex = timeQueue.get(0);
            timeQueue.remove(0);
            origin = next_vertex;
        }
    }

    public static void backtrackCostTable(String origin, String destination, boolean timed) {
        System.out.println("Backtracking from " + destination + " to " + origin);
        ArrayList<String> path = new ArrayList();
        String current = destination;
        System.out.println("Current position : " + current);
        System.out.println("Current position : " + origin);
        while (!current.equals(origin)) {
            path.add(current);
            for (int v = 0; v < vertices.size(); v++) {
                if (vertices.get(v).getCode().equals(current)) {
                    current = cost_table.get(vertices.get(v)).getParent().getCode();
                    break;
                }
            }
        }
        path.add(origin);
        ArrayList<String> reversePath = new ArrayList();
        for (int r = path.size() - 1; r >= 0; r--) {
            reversePath.add(path.get(r));
        }
        System.out.println("The path from " + origin + " to " + destination + " goes as follows");
        for (int p = 0; p < path.size(); p++) {
            System.out.println(reversePath.get(p));
        }
        if (timed) {
            System.out.println("This took " + reversePath.size() + " hours in real time");
        }
    }

    public static void findWidestCoverage() {
        ArrayList<String> tmp_airports = new ArrayList<>();
        String tmp_airline = null;
        int tmp_max_coverage = 0;
        for (int a = 0; a < all_airlines.size(); a++) {
            ArrayList<String> airports_covered = runPrimsOnAirline(all_airlines.get(a));
            if (airports_covered.size() > tmp_max_coverage) {
                tmp_max_coverage = airports_covered.size();
                tmp_airports = airports_covered;
                tmp_airline = all_airlines.get(a);
            }
        }
        System.out.println(tmp_airline + " has the widest coverage by " + tmp_max_coverage + " airports");
    }

    public static ArrayList<String> runPrimsOnAirline(String a) {
        ArrayList<String> a_visited = new ArrayList<>();
        ArrayList<Edge> a_edges = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getAIRLINE_CODE().equals(a)) {
                a_edges.add(edges.get(i));
            }
        }
        ArrayList<String> a_vertices = new ArrayList();
        for (int e = 0; e < a_edges.size(); e++) {
            tmpEdge = a_edges.get(e);
            if (!a_vertices.contains(tmpEdge.getDESTINATION_CODE())) {
                a_vertices.add(tmpEdge.getDESTINATION_CODE());
            }
            if (!a_vertices.contains(tmpEdge.getSOURCE_CODE())) {
                a_vertices.add(tmpEdge.getSOURCE_CODE());
            }
        }
        String start = a_vertices.get(0);
        a_visited.add(start);
        boolean action = true;
        int itercounter = 0;

        while (action) {
            tmpPath.setDISTANCE(-1);
            //System.out.println(itercounter);
            for (int p = 0; p < a_edges.size() -1; p++) {
                Edge tmpSource = a_edges.get(p);
                if (a_visited.contains(tmpSource.getSOURCE_CODE()) && !a_visited.contains(tmpSource.getDESTINATION_CODE())) {
                        itercounter++;
                        //System.out.println(itercounter + ", " + tmpSource.getAIRLINE_CODE());
                        if ((float) tmpSource.getDISTANCE() > (float) tmpPath.getDISTANCE()) {
                            tmpPath = tmpSource;
                        }
                    }
                }
            if ((float) tmpPath.getDISTANCE() > -1 && !a_spanningTree.contains(tmpPath)) {
                a_spanningTree.add(tmpPath);
                a_visited.add(tmpPath.getDESTINATION_CODE());
                a_edges.remove(tmpPath);
            } else {
                //System.out.println(a + " covers " + a_edges.size() + " routes between " + a_vertices.size() + " airports");
                return a_visited;
            }
        }
        return null;
    }

    public static void compareBFSAndDFS(int times) {
        double acc_bfs_time = 0;
        double acc_dfs_time = 0;
        String winner = "";
        String loser = "";
        double difference = 0;
        for (int i = 0; i < times; i++) {
            //breath first
            Stopwatch bfs = new Stopwatch();
            singleAirline("CPH", "HNL", true);
            double end_bfs = bfs.elapsedTime();
            acc_bfs_time += end_bfs;
            //depth first
            Stopwatch dfs = new Stopwatch();
            singleAirline("CPH", "HNL", false);
            double end_dfs = dfs.elapsedTime();
            acc_dfs_time += end_dfs;
        }
        if (acc_bfs_time < acc_dfs_time) {
            winner = "Breath First";
            loser = "Depth First";
            difference = (acc_dfs_time - acc_bfs_time) / times;
        } else {
            winner = "Depth First";
            loser = "Breath First";
            difference = (acc_bfs_time - acc_dfs_time) / times;
        }

        System.out.println("The winner of this is the " + winner + " method, the loser is then the " + loser + " method, and the difference in computing time is " + difference + " seconds.");
    }
}
