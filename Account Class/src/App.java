public class App {
    //Part II
    public static void main(String[] args) throws Exception {
        Account ac1 = new Account(100);
        ac1.printSlip();
        ac1.deposit(300);
        ac1.printSlip();
        ac1.withdraw(23.45);
        ac1.printSlip();

        Account ac2 = new Account();
        ac1.transfer(ac2, 25);
        ac1.withdraw(1000);
        ac1.printSlip();
    }
}
