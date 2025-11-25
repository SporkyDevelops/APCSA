public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\n" + processNumbers(9990, 10000));
    }

    public static int productOfDigits(int num){
        int remaining = num / 10;
        int digit = num % 10;
        int product = digit;

        while (remaining > 0){
            digit = remaining % 10;
            remaining /= 10;
            product *= digit;
        }

        return product;
    }

    public static int processNumbers(int start, int end){
        int count = 0;

        for(int i = start; i <= end; i++){
            if(productOfDigits(i) >= 500){
                System.out.println(i + " - Large");
                count++;
            } else{
                System.out.println(i + " - Small");
            }
        }

        return count;
    }
}
