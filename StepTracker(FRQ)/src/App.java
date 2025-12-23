class StepTracker {
    private int minSteps;
    private int savedSteps;
    private int activeDays;
    private int days;

    public StepTracker(int minSteps){
        this.minSteps = minSteps;
    }

    public void addDailySteps(int steps){
        if(steps >= minSteps){
            activeDays++;
        }

        days++;
        savedSteps += steps;
    }

    public int activeDays(){
        return activeDays;
    }

    public double averageSteps(){
        if(days == 0){
            return 0;
        }

        return savedSteps/ (double) days;
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        StepTracker tr = new StepTracker(10000);
        System.out.println(tr.activeDays());
        System.out.println(tr.averageSteps());
        tr.addDailySteps(9000);
        tr.addDailySteps(5000);
        System.out.println(tr.activeDays());
        System.out.println(tr.averageSteps());
        tr.addDailySteps(13000);
        System.out.println(tr.activeDays());
        System.out.println(tr.averageSteps());  
        tr.addDailySteps(23000);
        tr.addDailySteps(1111);
        System.out.println(tr.activeDays());
        System.out.println(tr.averageSteps());  

    }
}
