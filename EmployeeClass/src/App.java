public class App {
    public static void main(String[] args) throws Exception {
        Employee ff = new Employee("Fred", "Flinstone", 35, "Bronto-Crane Operator", 33000);

        ff.setId("011-11-0088");
        ff.printPersonnelReport();

        System.out.println("\n|------------------------------------|");

        ff.giveRaise(3000);
        ff.printPersonnelReport();

        System.out.println("\n|------------------------------------|");

        ff.fire();
        ff.printPersonnelReport();

        System.out.println("\n|------------------------------------|");

        ff.increaseAge();
        ff.changePosition("Quarry Boss", 60000);
        ff.printPersonnelReport();
    }
}
