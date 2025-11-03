import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

//spaghetti code
public class App {
    
    public volatile boolean running = true;

    private List<String> menu;
    private int optionsIndex = 0;
    
    private static Terminal terminal;
    private Deck deck = new Deck();

    private Reader reader;

    private static final String ANSI_CLEAR_SCREEN = "\033[H\033[2J";

    //game states
    private int currency = 10;
    private boolean collectedPile = false;
    private boolean atTable = false;
    private boolean pauseDraw = false;
    private boolean shopping = false;
    private boolean ending = false;
    private int debounce = 0; //fixes weird error with multithreaded input

    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> house = new ArrayList<>();
    private int handScore = 0;
    private String player = null; //name of user

    //player states
    private int bet = 5;
    private boolean bust = false;
    private boolean stand = false;

    private ArrayList<Card> lastHand;
    private ArrayList<Card> lastDealer;

    private int wins = 0;
    
    //draws terminal
    public void run() throws IOException {
        deck.shuffle();
        InputHandler inputHandler = null;

        try {
    
            terminal = TerminalBuilder.builder()
                .jna(true)
                .system(true)
                .build();
            terminal.enterRawMode();

            reader = terminal.reader();
            LineReader tempReader = LineReaderBuilder.builder().terminal(terminal).build();

            System.out.println(ANSI_CLEAR_SCREEN);
            player = tempReader.readLine("Please input name to start: ");

            inputHandler = new InputHandler(reader, this);
            inputHandler.start();//thread stuff

            while (running) {

                if(!pauseDraw) draw();

                //uses math.rand to select random ending, some having a reaction with player name
                if(currency == 0){
                    ending = true;
                    pauseDraw = true;
                    System.out.println(ANSI_CLEAR_SCREEN);
                    int endMessage = (int) (Math.random()*10);

                    switch(endMessage){
                        case 0:
                            System.out.println("You shouldve brought more cash");
                            break;
                        case 1:
                            System.out.println("You've succumbed to debt " + player.trim());
                            break;
                        case 2:
                            System.out.println("The floor opened below you, you are dead " + player.trim());
                            break;
                        case 3:
                            System.out.println("Your family told you to call the hotline, you should've listened " + player.trim());
                            break;
                        case 4:
                            System.out.println("You'll never escape this place, " + player.trim());
                            break;
                        case 5:
                            System.out.println("You have no money, youre useless to this place " + player.trim());
                            break;
                        case 6:
                            System.out.println("You've lost the final hand");
                            break;
                        case 7: 
                            System.out.println("You'll never pay what you owe" + player.trim());
                            break;
                        case 8:
                            if(player.trim().equalsIgnoreCase("benny")){
                                System.out.println("Sorry you got twisted up in this scene.\n" + 
                                    "From where you're kneeling it must seem like an 18-carat run of bad luck. But, truth is..." +
                                    "the game was rigged from the start.");
                            } else if(player.trim().equalsIgnoreCase("daniel")){
                                System.out.println("Damnnnn Daniel, back at it again with the white Vans");
                            } else if(player.trim().equalsIgnoreCase("stan")){
                                System.out.println("And even if I could, it'd all be grey\n" + "But your picture on my wall\n" +
                                    "It reminds me that it's not so bad, it's not so bad");
                            } else if(player.trim().equalsIgnoreCase("speed")){
                                System.out.println("Speed.. my mom's kinda homeless.. i- I live with my dad");
                            } else {
                                System.out.println("Something something easter egg... uhhh something something");
                            }
                            break;
                        case 9:
                            System.out.println("I ran out of ideas");
                            break;
                        default:
                            break;
                    }

                    running = false;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            //handles shutting down application
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            System.out.println("\n\nApplication exiting");

            if (inputHandler != null){
                inputHandler.exitHandler();
            }

            if (terminal != null) {

                terminal.close();
            }
            System.exit(0);
        }
    }

    //handles input cases from InputHandler thread
    public void inputHandle(int input){
        
        switch(input){

            case 'q': 
                running = false;
                break;
            
            case 'h': 
                if(atTable && !stand){
                   hand.add(deck.deal());
                }
                break;
            
            case 'b':
                if(atTable && bet <= currency){
                    bet += 5;
                }

                if(bet > currency){
                    bet = currency;
                }
                break;

            case 's':
                stand = true;
                break;
            
            case 'a':
                optionsIndex = (optionsIndex - 1 + 3) % 3;
                break;
    
            case 'd': 
                optionsIndex = (optionsIndex + 1) % 3;
                break;
            
            case 'y':
                drawInterrupt(3);
                break;
            
            case 'z':
                currency += 500;
                break;
            
            case 'i':
                wins++;
                break;
            
            case 13:
                if(!ending){
                    
                    if(!atTable && !shopping) drawInterrupt(optionsIndex);

                    if(shopping) {
                        drawInterrupt(optionsIndex + 4); //shifts options index so instead of 0-2 its 4-6 (for switch)
                        debounce++; //weird error where menu item was selected, weird workaround i though of DONT ASK
                    }
                    
                }
                break;
            
            default: 
                break;
            
        
        }

    }

    //pauses drawing for menu events
    private void drawInterrupt(int selection){

        pauseDraw = true;

        System.out.println(ANSI_CLEAR_SCREEN);

        //self explanitory, takes the index from InputHandle method
            switch (selection) {
                case 0:
                    System.out.println("You move over to the table");
                    atTable = true;
                    break;
                case 1:
                    shopping = true;
                    pauseDraw = false;
                    optionsIndex = 0;
                    break;
                case 2:
                    if(!collectedPile){               
                        System.out.println("One of the machines spilled coins, Jackpot!");
                        currency += 50;
                        collectedPile = true;
                    } else{
                        System.out.println("There's nothing else of value");
                    }
                    break;
                
                case 3:
                    
                    System.out.println("__________________________");
                    System.out.println("| Controls:              |");
                    System.out.println("| 'a' -> menu up         |");
                    System.out.println("| 'd' -> menu down       |");
                    System.out.println("| ENTER -> menu select   |");
                    System.out.println("| 'h' -> hit             |");
                    System.out.println("| 's' -> stand           |");
                    System.out.println("| 'b' -> raise bet       |");
                    System.out.println("| 'q' -> exit app        |");
                    System.out.println("| 'z' -> add $500        |");
                    System.out.println("| 'i' -> add win         |");
                    System.out.println("|                        |");
                    System.out.println("| use inputs sparringly  |");
                    System.out.println("| if blank send input    |");
                    System.out.println("|                        |");
                    System.out.println("| Ace = 11 unless        |");
                    System.out.println("| over 21, if > 21    ↲  |");
                    System.out.println("| Ace = 1           ↲    |");
                    System.out.println("|                        |");
                    System.out.println("| Use any key to continue|");
                    System.out.println("|________________________|");
                    break;

                case 4:
                    if(debounce >= 1){ //fix for menu selecting option 2 for main menu, displaying message, and option 1 of shopping menu
                        if(currency >= 500 && wins >= 5){
                            System.out.println("You've bought your way out of this place, congrats!");

                            running = false;
                        } else {
                            System.out.println("Get your money up not your funny up");
                        }

                    }
                    break;
                case 5:
                    if(currency >= 50){
                        System.out.println("You may be stuck here but at least you're charitable");
                        currency -= 50;
                    } else {
                        System.out.println("Maybe you should be the one recieving donations...");
                    }
                    break;
                case 6:
                    shopping = false;
                    break;
                    
                default:
                    debounce = 0; //weird menu thing fix
                    pauseDraw = false;
                    break;
            }

            try{
                reader.read();
            }catch(IOException e){}

            pauseDraw = false;
    }

    //Pretty self explanitory
    private int calcHand(ArrayList<Card> hand, boolean player){
        int total = 0;
        int aces = 0;

        for(Card card : hand){
            String value = card.getValue();

            try{
                total += Integer.parseInt(value);
            } catch(NumberFormatException e) {
                switch(value){
                    case "J":
                    case "Q":
                    case "K":
                        total += 10;
                        break;
                    case "A":
                        total += 11;
                        aces++;
                        break;
                    default:
                        break;
                }
            }

        }

        if(aces > 0 && total > 21){
            total -= 10;
            aces--;
        }
        
        if(total > 21 && player){
            bust = true;
        }



        return total;
    }

    //draws to screen, loops until paused or not running
    private void draw() {
        System.out.println(ANSI_CLEAR_SCREEN);

        //drawing of top tool bar
        if(lastHand != null && lastDealer != null){
            String topBar = "\n|'y' for help |Chips: " + currency + " |Wins: " + wins + " |Last Hand: " + lastHand + " |Last Dealer-Hand: " + lastDealer + " |";

            //dynamic length
            for(int i = 0; i < topBar.length()-1; i++){
                System.out.print('_');
            }

            System.out.println(topBar);

            System.out.print('|');
            for(int i = 0; i < topBar.length()-3; i++){
                System.out.print('_');
            }
            System.out.print("|\n");

        } else { //continued
            String topBar = "\n|'Y' for help |Chips: " + currency + " |Wins: " + wins + " |";

            for(int i = 0; i < topBar.length()-1; i++){
                System.out.print('_');
            }
  
            System.out.println(topBar);

            System.out.print('|');
            for(int i = 0; i < topBar.length()-3; i++){
                System.out.print('_');
            }

            System.out.print("|\n");
        }

        //Main menu state
        if(!atTable && !shopping){
            System.out.print('\n');
            System.out.println("You're awoken by the sound of slot machines");
            System.out.println("On the right side of the room you spot a lonley blackjack table");
            System.out.println("On the left you see what appears to be a shop\n");

            menu = Arrays.asList("Go Right",
            "Go Left",
            "Investigate sound");

            for(int i = 0; i < 3; i++){
                String menuItem = menu.get(i);

                if(i == optionsIndex){
                    System.out.println(" > " + menuItem);
                } else{
                    System.out.println("  " + menuItem);
                }
            }
        }

        //Playing state
        if(atTable){
            int handScore = calcHand(hand, true);
            System.out.println(" bet: " + bet + " hand-value: " + handScore);

            System.out.println("Your Hand:");
            for(Card i : hand){
                System.out.println("__________");
                System.out.println("|"  + i +   "      |");
                System.out.println("|         |");
                System.out.println("|         |");
                System.out.println("|         |");
                System.out.println("|      "  + i +   "|");
                System.out.println("|_________|");            
            }

            if(stand){
                lastHand = hand;
                int dealerScore = calcHand(house, false);

                //dealer hits as long as their current hand < 16
                if (dealerScore < 16){
                    house.add(deck.deal());
                }

                System.out.println("Dealers Hand:");
                for(Card i : house){
                    if(i == house.get(0)){
                        System.out.println("__________");
                        System.out.println("|"  + i +   "      |");
                        System.out.println("|         |");
                        System.out.println("|         |");
                        System.out.println("|         |");
                        System.out.println("|      "  + i +   "|");
                        System.out.println("|_________|");
                    }

                    System.out.println("__________");
                    System.out.println("|         |");
                    System.out.println("|         |");
                    System.out.println("|         |");
                    System.out.println("|         |");
                    System.out.println("|         |");
                    System.out.println("|_________|");

                }
                
                //win conditions for blackj hand
               if(dealerScore >= 16){ //another weird workaround
                    System.out.println(ANSI_CLEAR_SCREEN);

                    lastDealer = house;

                    if(dealerScore > handScore && dealerScore <= 21){
                        System.out.println("You should've hit again");

                        try {
                        Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        currency -= bet;

                    } else if(handScore > dealerScore && !bust){
                        System.out.println("You cheated the system, we'll remember this...");
                        
                        try {
                        Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        wins++;
                        currency += bet;

                    } else if(dealerScore == handScore && !bust){
                        System.out.println("The house always wins");

                        try {
                        Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        e.printStackTrace();
                        }

                        currency -= bet;

                    } else {
                        System.out.println("This is awkward, dealer bust");

                        try {
                        Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        wins++;
                        currency += bet;
                    }

                    hand = new ArrayList<>();
                    house = new ArrayList<>();
                    bet = 5;
                    stand = false;
                    atTable = false;
                    optionsIndex = 0;
                }
            }

            if(bust){
                System.out.println("Bust!");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}

                hand = new ArrayList<>();
                currency -= bet;
                bet = 5;
                bust = false;
                atTable = false;
            }
            
        }
        //second menu for shoppinh
        if(shopping){
            System.out.println("Welcome to the shop!");

            menu = Arrays.asList("Buy your ticket out - $500 & 5 wins",
            "Donate to the poor - $50",
            "Return");

            for(int i = 0; i < 3; i++){
                String menuItem = menu.get(i);

                if(i == optionsIndex){
                    System.out.println(" > " + menuItem);
                } else{
                    System.out.println("  " + menuItem);
                }
            }
        }


    }


    public static void main(String[] args) {

        try {
            new App().run();
        } catch (IOException e) {
            System.err.println("Error initializing: " + e.getMessage());
        }

    }
}
