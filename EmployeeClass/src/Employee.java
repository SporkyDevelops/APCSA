import java.text.DecimalFormat;

public class Employee {
    private String first_name, last_name, id_num, title = null;
    private int age;
    private double salary;

    public Employee(){}

    public Employee(String first_name, String last_name, int age, String id_num, String title, double salary){
        this.first_name = first_name;
        this.last_name = last_name;
        this.id_num = id_num;
        this.title = title;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String first_name, String last_name, int age, String title, double salary){
        this.first_name = first_name;
        this.last_name = last_name;
        id_num = "000-00-0000";
        this.title = title;
        this.age = age;
        this.salary = salary;
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
    public void setId(String id_num){
        this.id_num = id_num;
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
    public void fire(){
        title = "terminated";
        salary = 0;
    }

    public void printPersonnelReport(){
        DecimalFormat salaryFormat = new DecimalFormat("#,##0.00");

        System.out.println(last_name + ", " + first_name + " " + title);
        if(id_num != null){
            System.out.println("ID: " + id_num + " | Salary: $" + salaryFormat.format(salary) + " | Age: " + age);
        } else {
            System.out.println("Salary: $" + salaryFormat.format(salary) + " | Age: " + age);
        }
    }

}
