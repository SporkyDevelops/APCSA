
public class Employee {
    private String first_name, last_name, title;
    private int age;
    private double salary;
    private Account acc;

    private String id_num;
    private static int availableID = 1;

    public Employee(){
        first_name = " ";
        last_name = " ";
        id_num = " ";
        title = " ";
    }

    public Employee(String first_name, String last_name, int age, String title, double salary){
        this.first_name = first_name;
        this.last_name = last_name;
        this.title = title;
        this.age = age;
        this.salary = salary;
        id_num = String.valueOf(availableID);
        availableID++;
    }

    public Employee(Account acc, int age, String title, double salary){
        first_name = acc.getFirst();
        last_name = acc.getLast();
        
        this.acc = acc;
        this.age = age;
        this.title = title;
        this.salary = salary;

        id_num = String.valueOf(availableID);
        availableID++;
    }

    public void trim(){
        first_name = first_name.trim();
        last_name = last_name.trim();
        title = title.trim();
    }

    public String getFirst(){
        return first_name;
    }

    public String getLast(){
        return last_name;
    }

    public String getId(){
        return id_num;
    }

    public double getSalary(){
        return salary;
    }

    public void increaseAge(){
        age++;
    }

    public void giveRaise(double num){
        salary += num;
    }

    public void changePosition(String title, double salary){
        this.title = title;
        this.salary = salary;
    }

    public void pay(){
        acc.deposit(salary/12);
    }

    public void fire(){
        title = "terminated";
        salary = 0;
    }

    public void printPersonnelReport(){
    
    System.out.println("===================================");
    System.out.printf("| %-33s |\n", "EMPLOYEE PAYSLIP"); // Header centered
    System.out.println("-----------------------------------");

    String fullNameAndTitle = last_name + ", " + first_name + " (" + title + ")";
    System.out.printf("| %-33s |\n", "Name & Title:");
    System.out.printf("| %-33s |\n", fullNameAndTitle);
    System.out.println("-----------------------------------");

    System.out.printf("| %-33s |\n", "Employee ID:");
    System.out.printf("| %-33s |\n", id_num);
    System.out.println("-----------------------------------");

    System.out.printf("| %-33s |\n", "Age:");
    System.out.printf("| %-33s |\n", age);
    System.out.println("-----------------------------------");

    System.out.printf("| %-33s |\n", "Annual Salary:");
    System.out.printf("| $%,32.2f |\n", salary); 
    System.out.println("===================================\n");
    }

    public String toString(){
        return first_name + ", " + last_name + " (" + title + ")";
    }

}
