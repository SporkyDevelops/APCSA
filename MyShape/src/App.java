import java.text.DecimalFormat;
import java.util.Scanner;

public class App {

        //ANSI Styling
        final static String CLEAR_ANSI = "\u001B[0m";
        final static String ANSI_RED = "\u001B[31m";
        final static String ANSI_GREEN = "\u001B[32m";
        final static String ANSI_BLUE = "\u001B[34m";
        final static String ANSI_MAGENTA = "\u001B[35m";
        final static String ANSI_CYAN = "\u001B[36m";

        //Clears screen and resets cursor
        final static String CLEAR_SCREEN = "\u001B[2J\u001B[H";

        //static String (stores menu selection)
        static String selection = null;

        //formats to 5 decimal places
        static DecimalFormat df = new DecimalFormat("#.####");


    static void perimeter(double sideA, double sideB, double sideC){

        //double perimeter = 0;

        if(selection.equals("Rectangle")){
            //Rectangle -> Length+Width+Length+Width
            System.out.println("The " + ANSI_CYAN + "PERIMETER" + CLEAR_ANSI + " of your Rectangle is: " + ANSI_GREEN + df.format(sideA + sideB + sideA + sideB) + CLEAR_ANSI);

            //perimeter = sideA+sideB+sideA+sideB;
        } else if(selection.equals("Circle")){
            // Cirlce -> 2πr
            System.out.println("The " + ANSI_CYAN + "CIRCUMFERENCE" + CLEAR_ANSI + " of your Circle is: " + ANSI_GREEN + df.format(2*(Math.PI*sideA)) + CLEAR_ANSI);

            //perimeter = 2*(Math.PI*sideA);
        } else {
            //Triangle -> (self explanitory)
            System.out.println("The " + ANSI_CYAN + "PERIMETER" + CLEAR_ANSI + " of your Triangle is: " + ANSI_GREEN + df.format(sideA + sideB + sideC) + CLEAR_ANSI);

            //perimeter = sideA+sideB+sideC;
        }
        
        //return perimeter;
    }

    static double area(double sideA, double sideB){
        //variable to hold return value
        double area = 0;

        if(selection.equals("Rectangle")){
            //Rectangle -> Length * Width
            area = sideA*sideB;
        } else if(selection.equals("Circle")){
            //Circle -> π*r^2
            area = Math.PI*(sideA*sideA);
        } else {

            //Triangle -> (Base*Height)/2
            area = (sideA*sideB)/2;
        }

        //returns calculated value
        return area;
    }

    public static void main(String[] args) throws Exception {

        //variables for dimensions
        double length;
        double width;
        double triangleSide;
        double height;

        Scanner scan = new Scanner(System.in);

        boolean menu = true;
        String arrow = "➜ ";

        //Shapes array and variable for tracking index (for menu)
        String[] shapes = {"Rectangle", "Circle", "Triangle"};
        int shapeIndex = 0;

        //While loop to draw menu
        while(menu) {
            
            //Clears screen every loop
            System.out.println(CLEAR_SCREEN);

            System.out.println(ANSI_MAGENTA + "Select A Shape:" + CLEAR_ANSI);

            //Iterates through and if the shapes location in array matches the shapeIndex it adds the arrow and highlights it in green
            for(int i = 0; i < shapes.length; i++){

                if(i == shapeIndex){
                    System.out.println(ANSI_GREEN + "\033[1m" + arrow + shapes[i] + "\033[0m" + CLEAR_ANSI);

                    //Sets variable 'selection' to current shape (as long as it matches the shapeIndex)
                    selection = shapes[i];
                }
                else{
                    System.out.println(" " + shapes[i]);
                }
            }

            System.out.print("\nSelect with " + ANSI_MAGENTA + "'Enter'" + CLEAR_ANSI);
            System.out.print(", scroll by entering " + ANSI_MAGENTA + "'s'" + CLEAR_ANSI);
            System.out.print(", or quit by entering " + ANSI_MAGENTA + "'q'" + CLEAR_ANSI + ": ");
            String input = scan.nextLine();

            //Checks if input is empty (ENTER is pressed by itself)
            if(input.isEmpty()){
                
                //Exits menu and prints selection
                System.out.print(CLEAR_SCREEN);
                System.out.println(ANSI_CYAN + selection + CLEAR_ANSI);
                menu = false;

            } else if(input.equalsIgnoreCase("q")){ //checks if 'q' is typed (and entered)

            //Prints exit message and exits program with status '0'
                System.out.print(CLEAR_SCREEN);
                System.out.print(ANSI_RED + "Exiting");
                System.exit(0);

            } else if(input.equalsIgnoreCase("s")){ //checks if 's' is typed (and entered)

                //adds 1 to shapeIndex (moves selection down by one) and loops back to first shape if value is > size of array
                shapeIndex = (shapeIndex + 1) % shapes.length;

            } else { //if anything else is typed

                System.out.print(CLEAR_SCREEN);
                //error handling
                System.out.print(ANSI_RED + "Invalid input type [" + input + "]");
                System.exit(0);
            }
        }

        if(selection.equals("Rectangle")){

            //Takes input for rectangle dimensions
            System.out.print("Please input Rectangle length: ");
            length = scan.nextDouble();
            System.out.print("Please input Rectangle width: ");
            width = scan.nextDouble();

            //calculates perimeter and area of rectangle, 2 arguments (length and width)
            System.out.println(CLEAR_SCREEN);
            perimeter(length, width, 0);
            //System.out.println("The " + ANSI_CYAN + "PERIMETER" + CLEAR_ANSI + " of your Rectangle is: " + ANSI_GREEN + df.format(perimeter(length, width, 0)) + CLEAR_ANSI);
            System.out.println("The " + ANSI_CYAN + "AREA" + CLEAR_ANSI + " of your Rectangle is: " + ANSI_GREEN + df.format(area(length, width)) + CLEAR_ANSI);


        } else if(selection.equals("Circle")){

            //Takes input for circles radius and stores it in double 'length'
            System.out.print("Please input Circle radius: ");
            length = scan.nextDouble();

            //calculates perimeter and area, single argument (radius)
            System.out.print(CLEAR_SCREEN);
            perimeter(length, 0, 0);
            //System.out.println("The " + ANSI_CYAN + "CIRCUMFERENCE" + CLEAR_ANSI + " of your Circle is: " + ANSI_GREEN + df.format(perimeter(length, 0, 0)) + CLEAR_ANSI);
            System.out.println("The " + ANSI_CYAN + "AREA" + CLEAR_ANSI + " of your Circle is: " + ANSI_GREEN + df.format(area(length, 0)) + CLEAR_ANSI);

        } else {

            //Take inputs to determine Triangle dimensions
            System.out.print("Please input Triangle Side A length: ");
            length = scan.nextDouble();
            //Value for side B/Base
            System.out.print("Please input Triangle Side B \033[1m(Base)\033[0m length: ");
            width = scan.nextDouble();
            System.out.print("Please input Triangle Side C length: ");
            triangleSide = scan.nextDouble();
            System.out.println("-------------------------------------------");
            System.out.print("Please input Triangle Height: ");
            height = scan.nextDouble();

            //Calls methods to calculate the area and perimeter
            //Uses side B for Base in area calculation
            System.out.print(CLEAR_SCREEN);
            perimeter(length, width, triangleSide);
            //System.out.println("The " + ANSI_CYAN + "PERIMETER" + CLEAR_ANSI + " of your Triangle is: " + ANSI_GREEN + df.format(perimeter(length, width, triangleSide)) + CLEAR_ANSI);
            System.out.println("The " + ANSI_CYAN + "AREA" + CLEAR_ANSI + " of your Triangle is: " + ANSI_GREEN + df.format(area(width, height)) + CLEAR_ANSI);

        }

        scan.close();
    }
}
