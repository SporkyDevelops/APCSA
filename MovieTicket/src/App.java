import java.util.Scanner;

public class App {

    static int age;
    static String showTimeType;
    static boolean in3d;

    final static String CLEAR_SCREEN = "\u001B[2J\u001B[H";

    public static void printReceipt(){

        //done first so discount is set to true
        double price = Ticket.mPrice(age, showTimeType, in3d);

        String type = "Adult ($9.00)";
        if(age >= 60){
            type = "Senior ($5.00)";
        } else if(age < 10){
            type = "Child ($6.00)";
        }

        System.out.println(CLEAR_SCREEN);
        System.out.println("Eligible Ratings: " + Ticket.mRating(age));
        System.out.println("Ticket Type: " + type);
        
        if(Ticket.discount){
            System.out.println("- Matinee Discount: -$1.00");
        }
        if(in3d){
            System.out.println("+ 3D Surcharge: +$2.00");
        }

        System.out.println("==============================");
        System.out.print("\033[0;1mTOTAL: $" );
        System.out.printf("%.2f%n", price);
    }

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        boolean errCatch = true;

        //Take inputs
        while(errCatch){
            System.out.print(CLEAR_SCREEN);
            System.out.println("Please input age (1-125): ");

            if(sc.hasNextInt()){
                age = sc.nextInt();
                sc.nextLine();

                if(age > 0 && age <= 125){
                    errCatch = false;
                }
                else{
                    System.out.println("Invalid age");
                    Thread.sleep(1500);
                    System.out.println(CLEAR_SCREEN);
                    
                }
                
            }
            else{
                //Error catching
                System.out.println("Invalid age");
                Thread.sleep(1000);
                System.out.println(CLEAR_SCREEN);
                //Clear input buffer
                sc.nextLine();
            }
        }

        errCatch = true;

        // while(errCatch){
        //     System.out.println(CLEAR_SCREEN + "Please input age (int): ");

        //     try{
        //         age = sc.nextInt();
        //         errCatch = false;

        //         if(age < 0){
        //             errCatch = true;
        //             throw new Exception();
        //         }
        //     }
        //     catch(Exception e){
        //         System.out.println(CLEAR_SCREEN + "Invalid input, please try again!");
        //         sc.nextLine();
        //         //wait 5
        //         Thread.sleep(1000);
        //     }
        // }

        while(errCatch){
            System.out.print(CLEAR_SCREEN);
            System.out.println("Please input Show time (Matinee/Evening): ");

            showTimeType = sc.nextLine();

            if(showTimeType.equalsIgnoreCase("Matinee") || showTimeType.equalsIgnoreCase("Evening")){
                errCatch = false;
            }
            else{
                System.out.println("Invalid time");
                Thread.sleep(1500);
                System.out.println(CLEAR_SCREEN);
            }
        }

        System.out.println(showTimeType);
        errCatch = true;

        // in3d = sc.nextBoolean();
        while(errCatch){
            System.out.print(CLEAR_SCREEN);
            System.out.println("Is your viewing in 3D? (true/false): ");

            if(sc.hasNextBoolean()){
                in3d = sc.nextBoolean();
                sc.nextLine();

                errCatch = false;
                
            }
            else{
                System.out.println("Invalid input");
                Thread.sleep(1500);
                System.out.println(CLEAR_SCREEN);
                sc.nextLine();
            }
        }

        sc.close();

        printReceipt();

    }

}
