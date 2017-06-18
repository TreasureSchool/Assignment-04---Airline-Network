package NewGraphs;

/**
 * @author Joachim
 */
public class Edge {
    
    private String AIRLINE_CODE;
    private String SOURCE_CODE;
    private String DESTINATION_CODE;
    private double DISTANCE;
    private double TIME;
    
    public Edge() {
        
    }
    
    public Edge(String AIRLINE_CODE, String SOURCE_CODE, String DESTINATION_CODE, double DISTANCE, double TIME) {
        this.AIRLINE_CODE = AIRLINE_CODE;
        this.SOURCE_CODE = SOURCE_CODE;
        this.DESTINATION_CODE = DESTINATION_CODE;
        this.DISTANCE = DISTANCE;
        this.TIME = TIME;
    }

    public String getAIRLINE_CODE() {
        return AIRLINE_CODE;
    }

    public void setAIRLINE_CODE(String AIRLINE_CODE) {
        this.AIRLINE_CODE = AIRLINE_CODE;
    }

    public String getSOURCE_CODE() {
        return SOURCE_CODE;
    }

    public void setSOURCE_CODE(String SOURCE_CODE) {
        this.SOURCE_CODE = SOURCE_CODE;
    }

    public String getDESTINATION_CODE() {
        return DESTINATION_CODE;
    }

    public void setDESTINATION_CODE(String DESTINATION_CODE) {
        this.DESTINATION_CODE = DESTINATION_CODE;
    }

    public double getDISTANCE() {
        return DISTANCE;
    }

    public void setDISTANCE(double DISTANCE) {
        this.DISTANCE = DISTANCE;
    }

    public double getTIME() {
        return TIME;
    }

    public void setTIME(double TIME) {
        this.TIME = TIME;
    }
    
}
