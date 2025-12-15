import java.util.ArrayList;
import java.util.Scanner;

public class TestDriver {
    public static Scanner input = new Scanner(System.in);
    public final static String clearScreen = "\033[H\033[2J";

    private static Bank b1 = new Bank("Generic Bank");
    private static boolean running = true;
    
    public static void main(String[] args) throws Exception {

        String[] menu = {"Open Account", "Hire Employee", "Fire All", "Issue Pay", "Give Raises (3%)", "Fee Account", "Add Interest", "Total Funds", "Print Report", "Search First Name", "Search Account #", "Search ID #", "Exit"};

        /*  For assignment

            b1.printCompanyReport();
            System.out.println("\nTotal Funds: " + b1.calcTotalFunds() + "\n");

            b1.addInterest();
            System.out.println("\nTotal Funds: " + b1.calcTotalFunds() + "\n");

            System.out.println("Clear screen and contiue? (enter to continue)");
            input.nextLine();
            System.out.print(clearScreen);
            

            Employee nubsterEmployee = new Employee(nubster, 15, "teller", 15);
            b1.replaceTeller(nubsterEmployee);
            b1.printCompanyReport();

            System.out.println("Clear screen and contiue? (enter to continue)");
            input.nextLine();
            System.out.print(clearScreen);

            b1.issuePay();
            b1.printCompanyReport();

            noah.transfer(nubster, 1000);
            nubster.transfer(rayan, 900);
            rayan.transfer(oniel, 800);
            oniel.transfer(noah, 700);
            noah.transfer(rayan, 600);
            nubster.transfer(oniel, 500);
            rayan.transfer(nubster, 400);
            oniel.transfer(noah, 300);

            System.out.println("Clear screen and contiue? (enter to continue)");
            input.nextLine();
            System.out.print(clearScreen);

            b1.printCompanyReport();
            input.close();
        */
        int mIndex = 0;
        while(running){

            String selection = null;
            System.out.println("-===========================-");
            for(int i = 0; i < menu.length; i++){
                if(i == mIndex){
                    System.out.printf("| %-25s |\n", "> " + menu[i]);
                    
                } else {
                    System.out.printf("| %-25s |\n", menu[i]);
                }
            }
            
            // System.out.printf("| %-25s |\n", "1. Open Account");
            // System.out.printf("| %-25s |\n","2. Hire Employee");
            // System.out.printf("| %-25s |\n","3. Fire All");
            // System.out.printf("| %-25s |\n","4. Issue Pay");
            // System.out.printf("| %-25s |\n","5. Give Raises (3%)");
            // System.out.printf("| %-25s |\n","6. Fee Account");
            // System.out.printf("| %-25s |\n","7. Add Interest");
            // System.out.printf("| %-25s |\n","8. Total Funds");
            // System.out.printf("| %-25s |\n","9. Print Report");
            // System.out.printf("| %-25s |\n","10. Search First Name");
            // System.out.printf("| %-25s |\n","11. Search Account #");
            // System.out.printf("| %-25s |\n","12. Search ID #");
            // System.out.printf("| %-25s |\n","13. Exit");
            System.out.println("-===========================-");
            System.out.println("Use 'w', 's' to Navigate and 'e' to Select\n");

            selection = input.next().trim();

            if(selection.equalsIgnoreCase("w")){
                    mIndex = (mIndex + 1) % menu.length;
            } else if(selection.equalsIgnoreCase("e")){
                    menuLogic(mIndex);
            } else if(selection.equalsIgnoreCase("s")){
                mIndex = (mIndex + 1) % menu.length;
            }else {
                System.out.println("Invalid Input (Enter to Continue)");
                input.nextLine();
            }

            System.out.print(clearScreen);
        }
    }

    private static Account promptAccount(){
        System.out.println("----------- Open New Account -----------");
        System.out.println("Enter First Name: ");
        String fName = input.next();

        System.out.println("Enter Last Name: ");
        String lName = input.next();

        System.out.print("Enter Initial Balance: ");
        double balance = 0;

        try {
            balance = input.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid Input Defaulting to 0 (Enter to Continue)");
            input.nextLine();
        }

        input.nextLine();
        return new Account(balance, fName, lName);
    }

    private static Employee promptEmployee(Account linkedAcc){
        System.out.println("----------- Hire Employee -----------");

        System.out.println("Enter Age: ");
        int age = 0;

        try {
            age = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input Defaulting to 0 (Enter to Continue)");
            input.nextLine();
        }

        System.out.println("Enter Position: ");
        String title = input.next();

        System.out.print("Enter Salary: ");
        double salary = 0;

        try {
            salary = input.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid Input Defaulting to 0 (Enter to Continue)");
            input.nextLine();
        }

        input.nextLine();
        return new Employee(linkedAcc, age, title, salary);
    }

    private static Account selectAccount(Bank bank){
        ArrayList<Account> accountList = bank.getAccounts();

        for(int i = 0; i < accountList.size(); i++){
            System.out.println((i + 1) + ". " + accountList.get(i).getLast() + ", " + accountList.get(i).toString());
        }

        System.out.print("Enter selection #: ");
        int choice = 1;

        try {
            choice = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid selection - defaulting to account 1");
        }
        
        input.nextLine();

        if(choice < 1 || choice > accountList.size()){
            System.out.print("Invalid selection - defaulting to account 1");
            return accountList.get(0);
        }

        return accountList.get(choice - 1);
    }

    private static void menuLogic(int choice){
        String search;
        int searchI;
        switch (choice + 1) {
            case 1:
                System.out.print(clearScreen);
                Account newAcc = promptAccount();
                if(b1.addAccount(newAcc) == 0){
                    System.out.print(clearScreen);
                    System.out.println("Theres Already An Account Under This Name ('q' to Continue)");
                    input.next();
                }
                break;

            case 2:
                System.out.print(clearScreen);
                Account linkedAccount = new Account();
                if(!b1.getAccounts().isEmpty()){
                    linkedAccount = selectAccount(b1);
                } else {
                    System.out.println("No Accounts Available; Canceling Action ('q' to Continue)");
                    input.next();
                    break;
                }
                Employee newEmployee = promptEmployee(linkedAccount);
                if(b1.addEmployee(newEmployee) == 0){
                    System.out.print(clearScreen);
                    System.out.println("Theres Already An Employee Under This Name ('q' to Continue)");
                    input.next();
                }
                break;

            case 3:
                System.out.print(clearScreen);
                b1.fireAll();
                System.out.println("Terminated all employees");
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                break;

            case 4:
                System.out.print(clearScreen);
                b1.issuePay();
                System.out.println("Issued monthly pay to available employees");
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                break;

            case 5:
                System.out.print(clearScreen);
                b1.giveRaise(3);
                System.out.println("Issued 3% raises to all available employees");
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                break;

            case 6:
                System.out.print(clearScreen);
                Account feeAccount = new Account();
                if(!b1.getAccounts().isEmpty()){
                    feeAccount = selectAccount(b1);
                } else {
                    System.out.println("No Accounts Available; Canceling Action ('q' to Continue)");
                    input.next();
                    break;
                }
                System.out.print("Ammount to fee account: ");
                double fee = 0;
                try {
                    fee = input.nextDouble();
                } catch (Exception e) {
                    System.out.println("Invalid Input Canceling Action (Enter to Continue)");
                    input.nextLine();
                }
                if(fee != 0){
                    b1.chargeFees(feeAccount, fee);
                }
                break;

            case 7:
                System.out.print(clearScreen);
                b1.addInterest();
                System.out.println("Added interest to available accounts");
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                break;

            case 8:
                System.out.print(clearScreen);
                System.out.println("Total Funds: " + b1.calcTotalFunds());
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                break;

            case 9:
                System.out.print(clearScreen);
                b1.printCompanyReport();
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                input.nextLine();
                break;

            case 10:
                System.out.print(clearScreen);
                System.out.println("Enter First Name to search: ");
                search = input.next();
                System.out.println("Matching Accounts: ");
                System.out.println(b1.searchNameAccount(search));
                System.out.println("\nMatching Employes: ");
                System.out.println(b1.searchNameEmployee(search));
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                input.nextLine();
                break;
            case 11:
                System.out.print(clearScreen);
                System.out.println("Enter Account number to search: ");
                try {
                    searchI = input.nextInt();
                } catch (Exception e) {
                    input.next();
                   break;
                }
                System.out.println("Matching Accounts: ");
                System.out.println(b1.searchAccount(searchI));
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                input.nextLine();
                break;
            case 12:
                System.out.print(clearScreen);
                System.out.println("Enter ID number to search: ");
                try {
                    searchI = input.nextInt();
                } catch (Exception e) {
                    input.next();
                   break;
                }
                System.out.println("Matching Employees: ");
                System.out.println(b1.searchId(searchI));
                System.out.println("==== Enter 'q' to continue ===");
                input.next();
                input.nextLine();
                break;
            case 13:
                running = false;
                System.out.print(clearScreen);
                System.exit(0);
                break;

            default:
                input.nextLine();
                break;
        }
    }
}
