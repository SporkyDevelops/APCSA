import java.text.DecimalFormat;
import java.util.ArrayList;

//Part I

public class Account {

    private double balance;
    private int accountNumber;
    private static int nextAvailableNum = 1;
        //shared between class itself
        //only one copy applied to all objects

    private ArrayList<Account> transferAccounts = new ArrayList<>();
    private String name;
    DecimalFormat digits = new DecimalFormat("0000");

    public Account(){
        accountNumber = nextAvailableNum;
        nextAvailableNum++;
    }

    public Account(int balance){
        this.balance = balance;
        accountNumber = nextAvailableNum;
        nextAvailableNum++;
    }

    public Account(int balance, String name){
        this.balance = balance;
        accountNumber = nextAvailableNum;
        this.name = name;
        nextAvailableNum++;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double dAmmount){
        if(dAmmount > 0) {
            balance += dAmmount;
        } else {
            System.out.println("\nErr: Invalid deposit ammount\n");
        }
    }

    public void withdraw(double wAmmount){
        if(balance >= wAmmount){
            balance -= wAmmount;
        } else {
            System.out.println("\nErr: Check Available Balance --- $30 OverDraft Incured\n");
            balance -= 30;
        }
    }

    public void transfer(Account recipient, double tAmmount){
        if(balance > tAmmount){
            withdraw(tAmmount);
            recipient.deposit(tAmmount);

            transferAccounts.add(recipient);
        } else {
            System.out.println("\nErr: Check Available Balance --- $30 OverDraft Incured\n");
            balance -= 30;
        }


    }

    public void printSlip(){

        System.out.println("======= GenericBank™ =======");
        if(accountNumber != 0){
            System.out.println("| Account Num: " + digits.format(accountNumber));
        }
        if(name != null){
            System.out.println("| Account Name: " + name);
        }
            System.out.println("| Account Balance: $" + balance);

        if(!transferAccounts.isEmpty()){
            System.out.print("| Lifetime Transfers (Acc num): ");
            for(Account account : transferAccounts){
                System.out.print(account + ", ");
            }
            System.out.println();
        }

        System.out.println("============================");
    }

    public String toString(){
    int lastDigits = accountNumber % 1000;

        if(name != null){
            return name + ", " + digits.format(lastDigits);
        } else {
            return String.valueOf(digits.format(lastDigits));
        }
    }

}
