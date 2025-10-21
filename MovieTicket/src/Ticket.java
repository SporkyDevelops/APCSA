public class Ticket {

    static boolean discount = false;
    
    public static String mRating(int age){

        // if(age < 0){
        //     System.out.println(App.CLEAR_SCREEN);
        //     System.out.println("Err: invalid age");
        //     System.exit(0);
        // }

        if(age >= 17){
            return "G, PG, PG-13, R";
        } else if(age >= 13){
            return "G, PG, PG-13";
        } else if(age >= 10){
            return "G, PG";
        } else{
            return "G";
        }

    }

    public static double mPrice(int age, String showTimeType, boolean in3d){

        double base = 9.0;

        if(age >= 60){
            base = 5.0;
        } else if(age < 10){
            base = 6.0;
        }

        if(in3d){
            base += 2;
        }

        if(showTimeType.equalsIgnoreCase("matinee") && age >= 10){
            base -= 1;
            discount = true;
        }

        return base;
        
    }



}
