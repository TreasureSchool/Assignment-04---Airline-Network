package NewFunctions;

/**
 * @author Joachim
 */
public class compareBFSAndDFS {
    public String compareBFSAndDFS(int times) {
        double acc_bfs_time = 0;
        double acc_dfs_time = 0;
        String winner = "";
        String loser = "";
        double difference = 0;
        for (int i = 0; i < times; i++) {
            //breath first
            Stopwatch bfs = new Stopwatch();
            //single_airline('CPH', 'HNL', true);
            double end_bfs = bfs.elapsedTime();
            acc_bfs_time += end_bfs;
            //depth first
            Stopwatch dfs = new Stopwatch();
            //single_airline('CPH', 'HNL', false);
            double end_dfs = dfs.elapsedTime();
            acc_dfs_time += end_dfs;
        }
        if (acc_bfs_time > acc_dfs_time) {
            winner = "Breath First";
            loser = "Depth First";
            difference = (acc_dfs_time - acc_bfs_time) / times; 
        } else {
            winner = "Depth First";
            loser = "Breath First";
            difference = (acc_bfs_time - acc_dfs_time) / times; 
        }
        
        return "The winner of this is the " + winner + " method, the loser is then the " + loser + " method, and the difference in computing time is " + difference + " seconds.";
    }
}
