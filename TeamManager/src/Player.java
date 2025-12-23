public class Player {
    private String name;
    private String position;
    private double ppg;

    private WorkoutPlan workout;

    public String getName(){ return name; }
    public String getPosition(){ return position; }
    public double getPPG(){ return ppg; }

    public String toString(){
        return name + ": " + position + " | " + ppg + " AVG points (PPG) \n" + "Workout -> " + workout;
    }

}
