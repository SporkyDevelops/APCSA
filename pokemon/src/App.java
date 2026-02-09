import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        File pokedex = new File("assets/pokedex.csv");
            
        Scanner read = new Scanner(pokedex);
        ArrayList<Pokemon> poke = new ArrayList<>();

        read.nextLine();
        while(read.hasNextLine()){
            String[] current = read.nextLine().split(",");
            poke.add(new Pokemon(current[1], current[2], current[3], Integer.parseInt(current[4]), Integer.parseInt(current[5]), Integer.parseInt(current[6]), Integer.parseInt(current[7])));
        }

        read.close();

        //fire, water, fighting, ground, dragon, psychic, electric 
        int[] hp = {0, 0, 0, 0, 0, 0, 0};
        int[] count = {0, 0, 0, 0, 0, 0, 0};
        int[] count2 = {0, 0, 0, 0, 0, 0, 0};
        String[] types = {"Fire", "Water", "Figthing", "Ground", "Dragon", "Psychic", "Electric"};

        for(Pokemon p : poke){
            String type = p.getType();
            String type2 = p.getType2();

            if(type.equals("Fire")){
                count[0]++;
                hp[0] += p.getHp();
            }
            if(type.equals("Water")){
                count[1]++;
                hp[1] += p.getHp();
            }
            if(type.equals("Fighting")){
                count[2]++;
                hp[2] += p.getHp();
            }
            if(type.equals("Ground")){
                count[3]++;
                hp[3] += p.getHp();
            }
            if(type.equals("Dragon")){
                count[4]++;
                hp[4] += p.getHp();
            }
            if(type.equals("Psychic")){
                count[5]++;
                hp[5] += p.getHp();
            }
            if(type.equals("Electric")){
                count[6]++;
                hp[6] += p.getHp();
            }


            if(type2.equals("Fire")){
                count2[0]++;
            }
            if(type2.equals("Water")){
                count2[1]++;
            }
            if(type2.equals("Fighting")){
                count2[2]++;
            }
            if(type2.equals("Ground")){
                count2[3]++;
            }
            if(type2.equals("Dragon")){
                count2[4]++;
            }
            if(type2.equals("Psychic")){
                count2[5]++;
            }
            if(type2.equals("Electric")){
                count2[6]++;
            }
        }

        Double[] averageHP = {hp[0]/(double)count[0], hp[1]/(double)count[1], hp[2]/(double)count[2], hp[3]/(double)count[3], hp[4]/(double)count[4], hp[5]/(double)count[5], hp[6]/(double)count[6]};

        System.out.println("Average HP per type (main type)");
        for(int i = 0; i < types.length; i++){
            System.out.print("| " + types[i] + " ");

            for(int j = 0; j < averageHP[i]; j++){
                System.out.print("*");
            }

            System.out.println("  " + averageHP[i]);
        }

        System.out.println("\n\n Occurences of Types (main type)");
        for(int i = 0; i < types.length; i++){
            System.out.print("| " + types[i] + " ");

            for(int j = 0; j < count[i]; j++){
                System.out.print("@");
            }

            System.out.println("  " + count[i]);
        }

        System.out.println("\n\n Occurences of types (type 2)");
        for(int i = 0; i < types.length; i++){
            System.out.print("| " + types[i] + " ");

            for(int j = 0; j < count2[i]; j++){
                System.out.print("@");
            }

            System.out.println("  " + count2[i]);
        }

        
    }

}
