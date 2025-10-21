import java.util.Scanner;

public class App {

    final static String CLEAR_SCREEN = "\u001B[2J\u001B[H";

    public static void main(String[] args) throws Exception {
       
        Scanner sc = new Scanner(System.in);

        //Part 1
        String s0 = "Computer Science Rocks";
        String s1 = "happy";
        String s2 = "Happy";
        String s3 = "Take me out to the ball game";

        System.out.println(CLEAR_SCREEN);
        System.out.println(s0.substring(s0.indexOf("Sci"), s0.indexOf("Sci") + 3));
        System.out.println("s1 = s2? " + s1.equals(s2));
        System.out.println("First occurence of ball is at index: " + s3.indexOf("ball"));
        // End part 1

        System.out.println("\nPress enter to continue");
        if(sc.nextLine().isBlank()){
            System.out.println(CLEAR_SCREEN);
        }

        // Part 2
        System.out.println("Enter date in mm/dd/yyyy format: ");
        String date = sc.nextLine();

        System.out.println("Enter SSN: ");
        String ssn = sc.nextLine();

        System.out.println("Enter string to scroll: ");
        String scroll = sc.nextLine();

        System.out.println("Enter strings to extract from (3): ");
        System.out.print("str One: ");
        String extract = sc.nextLine();
        System.out.print("str Two: ");
        String extract1 = sc.nextLine();
        System.out.print("str Three: ");
        String extract2 = sc.nextLine();

        System.out.println("Enter Name to format: ");
        String name = sc.nextLine();

        sc.close();

        System.out.println(CLEAR_SCREEN);

        System.out.println(StringStuff.changeFormat(date));
        System.out.println(StringStuff.eliminateDashes(ssn));
        System.out.println(StringStuff.scroll(scroll));
        System.out.println(StringStuff.extract(extract, extract1, extract2));
        System.out.println(StringStuff.convertName(name));
    }
}
