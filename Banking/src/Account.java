import java.text.DecimalFormat;
import java.util.ArrayList;

//Part I

public class Account {

    private double balance;
    private int accountNumber;
    private static int nextAvailableNum = 1;
    private static final int OVERDRAFT = 30;

    //Array List to track transfers
    private ArrayList<Account> transferAccounts = new ArrayList<>();
    
    private ArrayList<Double> fees = new ArrayList<>();
    private String name_first, name_last;

    //Formats last 4 digits (i.e 1 is 0001)
    DecimalFormat digits = new DecimalFormat("0000");

    public Account(){
        name_first = " ";
        name_last = " ";
        accountNumber = nextAvailableNum;
        nextAvailableNum++;
    }

    public Account(double balance){
        this.balance = balance;
        accountNumber = nextAvailableNum;
        nextAvailableNum++;
    }

    public Account(double balance, String name, String lName){
        this.balance = balance;
        accountNumber = nextAvailableNum;
        name_first = name;
        name_last = lName;
        nextAvailableNum++;
    }

    public Account(String name, String lName){
        accountNumber = nextAvailableNum;
        name_first = name;
        name_last = lName;
        nextAvailableNum++;
    }

    public ArrayList<Double> getFees(){
        return fees;
    }

    public void addFee(double fee){
        fees.add(fee);
    }

    public double getBalance(){
        return balance;
    }

    public void trim(){
        name_first = name_first.trim();
        name_last = name_last.trim();
    }

    //Condition: Ammount !0 or less
    public void deposit(double dAmmount){
        if(dAmmount > 0) {
            balance += dAmmount;
        } else {
            System.out.println("\n" + name_first + "Err: Invalid deposit ammount\n");
        }
    }

    //Condition: Withdraw < available account balance
    public void withdraw(double wAmmount){
        if(balance >= wAmmount){
            balance -= wAmmount;
        } else {
            System.out.println("\n" + name_first + "Err: Check Available Balance --- $30 OverDraft Incured\n");
            balance -= OVERDRAFT;
        }
    }

    public String getFirst(){
        return name_first;
    }
    public String getLast(){
        return name_last;
    }

    public int getNum(){
        return accountNumber;
    }

    //Condition: balance !< transfer ammount
    public void transfer(Account recipient, double tAmmount){
        if(balance > tAmmount){
            withdraw(tAmmount);
            recipient.deposit(tAmmount);

            transferAccounts.add(recipient);
        } else {
            System.out.println("\n" + name_first + " Err: Check Available Balance --- $30 OverDraft Incured\n");
            balance -= OVERDRAFT;
        }


    }

    public void printSlip(String bankName){
        DecimalFormat balFormat = new DecimalFormat("#.##");

        System.out.println("======= " + bankName + "™ =======");

        System.out.println("| Account Num: " + digits.format(accountNumber));

        //prints if object was initialized with name
        if(name_first != " " && name_last != " "){
            System.out.println("| Account Name: " + name_first + ", " + name_last);
        }

        System.out.println("| Account Balance: $" + balFormat.format(balance));

        //prints out transfer list
        if(!transferAccounts.isEmpty()){
            System.out.print("| Lifetime Transfers (Acc num): ");
            for(Account account : transferAccounts){
                System.out.print(account + ", ");
            }
            System.out.println();
        }

        if(!fees.isEmpty()){
            System.out.print("| Lifetime Fees: ");
            for(Double d : fees){
                System.out.print(d + " ");
            }
            System.out.println();
        }

        System.out.println("============================");
    }

    //solely for transfer list
    public String toString(){
    int lastDigits = accountNumber % 1000;

        return "[#" + lastDigits + "] " + name_first + ", " + name_last;
    }

}
