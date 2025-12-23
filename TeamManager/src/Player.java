public class Player {
    private String name;
    private String position;
    private double ppg;
    private boolean signed;

    private WorkoutPlan workout;

    public Player(String name, String position, double ppg, boolean signed){
        this.name = name;
        this.position = position;
        this.ppg = ppg;
        this.signed = signed;
    }

    //getters
    public String getName(){ return name; }
    public String getPosition(){ return position; }
    public double getPPG(){ return ppg; }
    public boolean getSigned(){ return signed; }
    public WorkoutPlan getWorkoutPlan(){ return workout; }

    //setters
    public void setName(String name){ this.name = name; }
    public void setPosition(String position){ this.position = position; }
    public void setPPG(double ppg){ this.ppg = ppg; }
    public void setSigned(boolean signed){ this.signed = signed; }
    
    public void setWorkoutDuration(int duration){ workout.setDuration(duration); }
    public void setWorkoutIntensity(String intensity){ workout.setIntensity(intensity); }


    public String toString(){
        if(signed){
            return name + ": " + position + " (Signed) " + "| " + ppg + " AVG points (PPG) \n" + "Workout -> " + workout;
        }

        return name + ": " + position + " (Free Agent) " + "| " + ppg + " AVG points (PPG) \n" + "Workout -> " + workout;
    }

}
