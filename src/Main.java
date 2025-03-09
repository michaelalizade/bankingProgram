import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    static BigDecimal balance = new BigDecimal("100");
    static int currency;
    static int menuOption;

    static BigDecimal convertToEuro = new BigDecimal("0.93");
    static int depositAmount;
    static BigDecimal depositToEuro;
    static int withdrawAmount;

    static int interestDuration;
    static int investAmount;
    static BigDecimal investResult;
    static BigDecimal interestRate = new BigDecimal("0.02");

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
                case 4 -> investFunds();
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

        BigDecimal depositValue = new BigDecimal(String.valueOf(depositAmount));


            // IF USD

        if(depositAmount <= 1){
            System.out.println("Amount cannot be lower than $1 or its equivalent!");
        }
        else{
            if(currency == 1){
                System.out.println("Depositing $" + depositAmount);
                balance = balance.add(depositValue);
                checkBalance();

            }
            // IF EUR
            else if(currency == 2){
                depositToEuro = depositValue.multiply(convertToEuro);
                System.out.printf("Depositing $%.2f\n", depositToEuro);
                balance = balance.add(depositToEuro);
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

        BigDecimal withdrawValue = new BigDecimal(String.valueOf(withdrawAmount));

            // IF USD

        if(currency == 1){

            if(withdrawValue.compareTo(balance) > 0){
                System.out.println("Insufficient funds!");
            }
            else if (withdrawValue.intValue() < 0){
                System.out.println("Amount must be greater than zero!");
            }
            else{
                balance = balance.subtract(withdrawValue);
                System.out.println("Withdrawing $" + withdrawAmount);
                checkBalance();
            }

        }
             // IF EURO

        else if(currency == 2){

            BigDecimal withdrawEuroValue = withdrawValue.multiply(convertToEuro);

            if(withdrawValue.multiply(convertToEuro).compareTo(balance) > 0){
                System.out.println("Insufficient funds!");
            }
            else if (withdrawValue.multiply(convertToEuro).doubleValue() < 0){
                System.out.println("Amount must be greater than zero!");
            }
            else {
                balance = balance.subtract(withdrawEuroValue);
                System.out.println("Withdrawing $" + withdrawEuroValue);
                checkBalance();
            }


        }





    }

    // COMPOUND INTEREST OVER 3,6,12,24 MONTHS

    static void investFunds(){

        chooseCurrency();

        System.out.println("---------------------");
        System.out.println("Enter amount to compound: ");
        investAmount = scanner.nextInt();

        System.out.println("Duration of contract:");
        System.out.println("1. 3 months");
        System.out.println("2. 6 months");
        System.out.println("3. 12 months");
        System.out.println("4. 24 months");

        System.out.print("Choose an option: ");
        interestDuration = scanner.nextInt();

        BigDecimal investValue = new BigDecimal(String.valueOf(investAmount));
        BigDecimal monthlyRate = interestRate.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);

        // IF USD

        if (currency == 1){

            if(investValue.compareTo(balance) > 0){
                System.out.println("Insufficient funds!");
            }
            else if(investAmount < 0){
                System.out.println("Amount cannot be less than zero!");
            }
            else{

                if(interestDuration == 1){

                    BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(3);

                    investResult = investValue.multiply(factor);
                    System.out.println("This investment will earn you $" + investResult.setScale(2, RoundingMode.HALF_UP));
                }
                else if(interestDuration == 2){

                    BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(6);

                    investResult = investValue.multiply(factor);
                    System.out.println("This investment will earn you $" + investResult.setScale(2, RoundingMode.HALF_UP));
                }
                else if(interestDuration == 3){

                    BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(12);

                    investResult = investValue.multiply(factor);
                    System.out.println("This investment will earn you $" + investResult.setScale(2, RoundingMode.HALF_UP));
                }
                else if(interestDuration == 4){

                    BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(24);

                    investResult = investValue.multiply(factor);
                    System.out.println("This investment will earn you $" + investResult.setScale(2, RoundingMode.HALF_UP));
                }

            }

        }

        // IF EURO

        else if(currency == 2){

            if(investValue.multiply(convertToEuro).compareTo(balance) > 0){
                System.out.println("Insufficient funds!");
            }
            else if(investValue.multiply(convertToEuro).doubleValue() < 0){
                System.out.println("Amount cannot be less than zero!");
            }
            else{

                if(interestDuration == 1){

                    BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(3);

                    investResult = investValue.multiply(factor).multiply(convertToEuro);
                    System.out.println("This investment will earn you $" + investResult.setScale(2, RoundingMode.HALF_UP));
                }
                else if(interestDuration == 2){

                    BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(6);

                    investResult = investValue.multiply(factor).multiply(convertToEuro);
                    System.out.println("This investment will earn you $" + investResult.setScale(2, RoundingMode.HALF_UP));
                }
                else if(interestDuration == 3){

                    BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(12);

                    investResult = investValue.multiply(factor).multiply(convertToEuro);
                    System.out.println("This investment will earn you $" + investResult.setScale(2, RoundingMode.HALF_UP));
                }
                else if(interestDuration == 4){

                    BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(24);

                    investResult = investValue.multiply(factor).multiply(convertToEuro);
                    System.out.println("This investment will earn you $" + investResult.setScale(2, RoundingMode.HALF_UP));
                }

            }


        }






    }

}