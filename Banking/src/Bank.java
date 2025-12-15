import java.util.ArrayList;

public class Bank {
    private String bankName;
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<Account> accounts = new ArrayList<Account>();
    
    public Bank(String bankName){
        this.bankName = bankName;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int addAccount(Account acc){
        acc.trim();
        if(checkAvailableAccount(acc)){
            accounts.add(acc);
        } else {
            return 0;
        }

        return 1;
    }

    public ArrayList<Account> searchNameAccount(String name){
        ArrayList<Account> temp = new ArrayList<>();
        for(Account acc : accounts){
            if (acc.getFirst().equalsIgnoreCase(name)) {
                temp.add(acc);
            }
        }

        return temp;
    }
    public ArrayList<Employee> searchNameEmployee(String name){
        ArrayList<Employee> temp = new ArrayList<>();
            for(Employee e : employees){
                if (e.getFirst().equalsIgnoreCase(name)) {
                    temp.add(e);
                }
            }

        return temp;
    }
    public ArrayList<Account> searchAccount(int num){
        ArrayList<Account> temp = new ArrayList<>();
            for(Account acc : accounts){
                if (acc.getNum() == num) {
                    temp.add(acc);
                }
            }

        return temp;
    }
    public ArrayList<Employee> searchId(int num){
        ArrayList<Employee> temp = new ArrayList<>();
            for(Employee e : employees){
                if (Integer.parseInt(e.getId()) == num) {
                    temp.add(e);
                }
            }

        return temp;
    }

    public int addEmployee(Employee e){
        e.trim();
        if(checkAvailableEmployee(e)){
            employees.add(e);
        } else {
            return 0;
        }

        return 1;
    }

    public boolean checkAvailableAccount(Account check){
        for(Account acc: accounts){
            if(acc.getFirst().equalsIgnoreCase(check.getFirst()) && acc.getLast().equalsIgnoreCase(check.getLast())){
                return false;
            }
        }

        return true;
    }

    public boolean checkAvailableEmployee(Employee e){
        for(Employee emp: employees){
            if(emp.getFirst() == e.getFirst() && emp.getLast() == e.getLast()){
                return false;
            }
        }

        return true;
    }

    public double calcTotalFunds(){
        int total = 0;
        for(Account acc : accounts){
            total += acc.getBalance();
        }

        return total;
    }

    public void chargeFees(Account acc, double fee){
        acc.withdraw(fee);
        acc.addFee(fee);
    }

    public void addInterest(){
        for(Account acc : accounts){
            if(acc.getBalance() > 0){
                acc.deposit(acc.getBalance() * 0.03);
            }
        }
    }

    public void giveRaise(double percent){
        for(Employee e : employees){
            e.giveRaise(e.getSalary()*(percent/100));
        }
    }

    public void fireAll(){
        for(Employee e : employees){
            e.fire();
        }
    }

    public void issuePay(){
        for(Employee e : employees){
            e.pay();
        }
    }

    public String getName(){
        return bankName;
    }

    public void printCompanyReport(){
        for(Employee e : employees){
            e.printPersonnelReport();
        }

        for(Account acc : accounts){
            acc.printSlip(bankName);
        }
    }
}
