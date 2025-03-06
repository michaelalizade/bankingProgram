import java.util.Scanner;

public class Main {

    static double balance = 100;
    static int currency;
    static int menuOption;

    static double convertToEuro = 0.93;
    static int depositAmount;
    static double depositToEuro;
    static int withdrawAmount;

    static int interest;

    static int start;
    static int end;


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        // MIKE'S JAVA BANKING PROGRAM

        System.out.println("Welcome to Mike's Java Banking Program!");


        do{
            System.out.print("Type 0 to start the program: ");
            start = scanner.nextInt();
            scanner.nextLine();
        }while(start != 0);


        do {

            System.out.println("****************");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Invest");
            System.out.println("5. Exit");
            menuOption = scanner.nextInt();

            switch(menuOption){
                case 1 -> checkBalance();
                case 2 -> depositFunds();
                case 3 -> withdrawFunds();
                case 5 -> end = 5;
            }

        }while(end != 5);

            // INVEST W/ COMPOUNDING INTEREST
                // 3 MONTHS
                // 6 MONTHS
                // 1 YEAR
                // 2 YEARS

        System.out.println("************************************************");
        System.out.println("Thank you for using Mike's Java Banking Program!");
        System.out.println("Check out my other projects ;)");

    }

    // USD/EUR OPERATION CHOICE

    static void chooseCurrency(){
        System.out.println("-------------------");
        System.out.println("Choose a currency: ");
        System.out.println("1: USD");
        System.out.println("2: Euro");
        currency = scanner.nextInt();

        while(currency != 1 && currency != 2){
            System.out.println("Invalid choice! Must choose one of given options");
            System.out.println("1: USD");
            System.out.println("2: Euro");
            currency = scanner.nextInt();
        }
    }

    // CHECK BALANCE

    static void checkBalance(){
        System.out.println("-------------------");
        System.out.printf("Balance: $%.2f\n", balance);
    }

    // DEPOSIT FUNDS (USD/EUR)

    static void depositFunds(){

        chooseCurrency();

        System.out.print("Enter amount to deposit: ");
        depositAmount = scanner.nextInt();
        scanner.nextLine();


            // IF USD

        if(depositAmount <= 1){
            System.out.println("Amount cannot be lower than $1 or its equivalent!");
        }
        else{
            if(currency == 1){
                System.out.println("Depositing $" + depositAmount);
                balance += depositAmount;
                checkBalance();

            }
            // IF EUR
            else if(currency == 2){
                depositToEuro = depositAmount * convertToEuro;
                System.out.printf("Depositing €%.2f\n", depositToEuro);
                balance += depositToEuro;
                checkBalance();
            }
        }



    }

    // WITHDRAW FUNDS (USD/EUR)

    static void withdrawFunds(){

        chooseCurrency();

        System.out.print("Enter amount to withdraw: ");
        withdrawAmount = scanner.nextInt();
        scanner.nextLine();

            // IF USD

        if(currency == 1){

            if(withdrawAmount > balance){
                System.out.println("Insufficient funds!");
            }
            else if (withdrawAmount < 0){
                System.out.println("Amount must be greater than zero!");
            }
            else{
                balance -= withdrawAmount;
                System.out.println("Withdrawing $" + withdrawAmount);
                checkBalance();
            }

        }
             // IF EURO

        else if(currency == 2){

            if(withdrawAmount * convertToEuro > balance){
                System.out.println("Insufficient funds!");
            }
            else if (withdrawAmount * convertToEuro < 0){
                System.out.println("Amount must be greater than zero!");
            }
            else {
                balance -= withdrawAmount * convertToEuro;
                System.out.println("Withdrawing €" + withdrawAmount);
                checkBalance();
            }


        }





    }

}