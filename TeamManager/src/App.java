import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class App {
    private final static String FILE = "team.json";
    private final static  String clearScreen = "\033[H\033[2J";
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean running = true;
        String[] menu = {"List Players", "View Report", "Edit Player"};


        List<Player> team = readJSON();
        
        int mIndex = 0;
        int choice = -1;
        while(running){

            System.out.println(clearScreen);

            String selection = null;
            System.out.println("-===========================-");
            for(int i = 0; i < menu.length; i++){
                if(i == mIndex){
                    System.out.printf("| %-25s |\n", "> " + menu[i]);
                    
                } else {
                    System.out.printf("| %-25s |\n", menu[i]);
                }
            }
            
            System.out.println("-===========================-");
            System.out.println("Use 'w', 's' to Navigate and 'e' to Select\n");

            selection = input.next().trim();

            if(selection.equalsIgnoreCase("w")){
                    mIndex = (mIndex - 1 + menu.length) % menu.length;
            } else if(selection.equalsIgnoreCase("e")){
                    choice = mIndex;
            } else if(selection.equalsIgnoreCase("s")){
                mIndex = (mIndex + 1) % menu.length;
            }else {
                System.out.println("Invalid Input (Enter to Continue)");
                input.nextLine();
            }

            System.out.println(clearScreen);

            switch(choice){
                case 0:
                    for(Player p : team){
                        if(p.getSigned()) {
                            System.out.println("() " + p.getName() + " -> " + p.getPosition() + "\n");
                        } else {
                            System.out.println("(Free Agent) " + p.getName() + " -> " + p.getPosition() + "\n");
                        }
                    }

                    System.out.println("===== Enter Any Character To Exit =====");
                    input.next();
                    choice = -1;
                    break;
                case 1:
                    System.out.println(clearScreen + playerReport(team));
                    System.out.println("===== Enter Any Character To Exit =====");
                    input.next();
                    choice = -1;
                    break;
                case 2:
                    updateJSON(team, playerReport(team).getName());
                    choice = -1;
                    break;
                default:
                    choice = -1;
                    break;
            }
            

        }


        input.close();
    }

    public static List<Player> readJSON(){
        Gson gson = new Gson();
        try (Reader reader = new FileReader("team.json")) {

            Type playerListType = new TypeToken<List<Player>>(){}.getType(); //Generic Type <List of player objects> TypeToken is from GSon 
            return gson.fromJson(reader, playerListType); // JSON to JAVA

        } catch (Exception e) {
            return new ArrayList<>(); //return empty
        }
    }

    public static int writeJSON(List<Player> player){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(FILE)){
            gson.toJson(player, writer);
            return 1;
        } catch (Exception e){
            return 0;
        }
    }

    public static int updateJSON(List<Player> team, String playerName){ //Update &|| search
        boolean found = false;
        int choice = 0;

        System.out.println(clearScreen);
        System.out.println("1. Change PPG");
        System.out.println("2. Change signed status");
        System.out.println("3. Change workout intensity");
        System.out.println("4. Change workout duration");

        try {
            choice = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid selection - defaulting to choice 1");
            choice = 1;
        }

        input.nextLine();

        if(choice < 1 || choice > 3){
            System.out.print("Invalid selection - defaulting to choice 1");
            choice = 1;
        }

        for(Player p : team){
            if(p.getName().equalsIgnoreCase(playerName)){
                System.out.println(clearScreen);
                System.out.println(p + "\n");

                double ppg = p.getPPG();
                boolean signed = p.getSigned();
                String intensity = p.getWorkoutPlan().getIntensity();
                int duration = p.getWorkoutPlan().getDuration();

                switch(choice){
                    case 1:
                        System.out.print("Input new Stat: ");
                        try {
                            ppg = input.nextDouble();
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                        p.setPPG(ppg);
                        break;
                    case 2:
                        System.out.print("Input Signed Status (true / false): ");
                        try {
                            signed = input.nextBoolean();
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                        p.setSigned(signed);
                        break;
                    case 3:
                        System.out.print("Input Workout Intensity (High / Medium / Low): ");
                        try {
                            intensity = input.nextLine();
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                        p.setWorkoutIntensity(intensity);
                        break;
                    case 4:
                        System.out.print("Input Workout Duration: ");
                        try {
                            duration = input.nextInt();
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                        p.setWorkoutDuration(duration);
                        break;
                    default:
                        break;
                }   

                found = true;
                break;
            }
        }

        if(found){
            writeJSON(team);
            return 1;
        } else {
            return 0;
        }
    }

    public static Player playerReport(List<Player> team){
        for(int i = 0; i < team.size(); i++){
            System.out.println(i+1 + ". " + team.get(i).getName()); 
        }

        System.out.print("Enter selection #: ");
        int choice = 1;

        try {
            choice = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid selection - defaulting to Player #1");
        }
        
        input.nextLine();

        if(choice < 1 || choice > team.size()){
            System.out.print("Invalid selection - defaulting to Player #1");
            return team.get(0);
        }

        
        return team.get(choice - 1);
    }
}
