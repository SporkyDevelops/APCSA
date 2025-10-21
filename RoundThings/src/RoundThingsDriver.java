import java.text.DecimalFormat;
import java.util.Scanner;

public class RoundThingsDriver {

    //ANSI Styling
    final static String CLEAR_ANSI = "\u001B[0m";
    final static String ANSI_MAGENTA = "\u001B[35m";
    final static String ANSI_CYAN = "\u001B[36m";

    final static String CLEAR_SCREEN = "\u001B[2J\u001B[H";


    static int index = 0;
    static DecimalFormat dc = new DecimalFormat("#.####");

    public static void main(String[] args) throws Exception {
        
        //input variable
        double radius;

        String[] calcType = {"area", "circumference", "surface area", "volume"};

        //Array to hold calculation results
        double[] results = new double[4];

        Scanner sc = new Scanner(System.in);

        System.out.print(CLEAR_SCREEN);

        System.out.print(ANSI_CYAN + "Input your objects radius: " + CLEAR_ANSI);
        radius = sc.nextDouble();
        sc.close(); 

        System.out.print(CLEAR_SCREEN);

        //Circle
        results[0] = RoundThings.calcAreaCircle(radius); // area
        results[1] = RoundThings.calcCircumference(radius); //cicumference
        /* ----------------------------------------------------------------
        Sphere */
        results[2] = RoundThings.calcSurfaceArea(radius); // surface area
        results[3] = RoundThings.calcVolumeSphere(radius); //volume

        for(int i = 0; i < 4; i++){
            //passes calculation name, its value, and the radius used to output
            output(calcType[i], results[i], radius);
            //index used for output
            index++;
        }

    }

    static void output(String type, double value, double rad){

        //first 2 values in results array are for circle -> as long as index < 2 the values belong to the circle
        if(index < 2){
            System.out.println("The " + ANSI_MAGENTA + type + CLEAR_ANSI + " of your Circle with a radius of " + ANSI_MAGENTA + rad + CLEAR_ANSI + " is: " + ANSI_CYAN + dc.format(value) + CLEAR_ANSI);
        } 
        //last 2 values in results array are for sphere -> if index > 2 the values belong to the sphere
        else { 
            System.out.println("The " + ANSI_MAGENTA + type + CLEAR_ANSI + " of your Sphere with a radius of " + ANSI_MAGENTA + rad + CLEAR_ANSI + " is: " + ANSI_CYAN + dc.format(value) + CLEAR_ANSI);
        }

    }
}
