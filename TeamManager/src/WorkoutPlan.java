public class WorkoutPlan {
    private String focus;
    private int duration;
    private String intensity;

    public String getFocus(){ return focus; }
    public int getDuration(){ return duration; }
    public String getIntensity(){ return intensity; }

    public String toString(){
        return focus + " (" + duration + " mins, " + intensity + ")";
    }
    
}
