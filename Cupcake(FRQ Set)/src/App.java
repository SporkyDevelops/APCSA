class CupcakeMachine {
    private int stock;
    private double cost;
    private int orders = 1;

    public CupcakeMachine(int stock, double cost){
        this.stock = stock;
        this.cost = cost;
    }

    public String takeOrder(int ammount){
        if(stock < ammount){
            return "Order cannot be filled";
        }

        String message = "Order Number: " + orders + " Cost: " + cost*ammount;
        orders++;
        stock -= ammount;

        return message;
    }
}

public class App {
    public static void main(String []args){
        CupcakeMachine c1 = new CupcakeMachine(10, 1.75);
        CupcakeMachine c2 = new CupcakeMachine(10, 1.5);

        System.out.println(c1.takeOrder(2) + "\n" + c1.takeOrder(3) + "\n" + c1.takeOrder(10) + "\n" + c1.takeOrder(1));
        System.out.println(c2.takeOrder(10));
    }
}
