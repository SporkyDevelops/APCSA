public class WorkoutPlan {
    private String focus;
    private int duration;
    private String intensity;

    public WorkoutPlan(String focus, int duration, String intensity){
        this.focus = focus;
        this.duration = duration;
        this.intensity = intensity;
    }

    //getters
    public String getFocus(){ return focus; }
    public int getDuration(){ return duration; }
    public String getIntensity(){ return intensity; }

    //setters
    public void setFocus(String focus){ this.focus = focus; }
    public void setDuration(int duration){ this.duration = duration; }
    public void setIntensity(String intensity){ this.intensity = intensity; }

    public String toString(){
        return focus + " (" + duration + " mins, " + intensity + ")";
    }
    
}
