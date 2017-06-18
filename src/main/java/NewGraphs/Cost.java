package NewGraphs;

/**
 * @author Joachim
 */
public class Cost {
    private boolean visited;
    private double cost;
    private Vertice parent;
    
    public Cost() {
        
    }
    
    public Cost(boolean visited, double cost, Vertice parent) {
        this.visited = visited;
        this.cost = cost;
        this.parent = parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Vertice getParent() {
        return parent;
    }

    public void setParent(Vertice parent) {
        this.parent = parent;
    }
    
    
}
