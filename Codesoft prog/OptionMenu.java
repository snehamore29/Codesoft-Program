import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;

class Account {
    private int CustomerNumber;
    private int PinNumber;
    private int CheckingBalance = 0;
    private int SavingBalance = 0;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$',###,###.##");

    public int setCustomerNumber(int CustomerNumber) {
        this.CustomerNumber = CustomerNumber;
        return CustomerNumber;
    }

    public int getCustomerNumber() {
        return CustomerNumber;
    }

    public int setPinNumber(int PinNumber) {
        this.PinNumber = PinNumber;
        return PinNumber;
    }

    public int getPinNumber() {
        return PinNumber;
    }

    public int getCheckingBalance() {
        return CheckingBalance;
    }

    public int getSavingBalance() {
        return SavingBalance;
    }

    public int calcCheckingWithdraw(int amount) {
        CheckingBalance = (CheckingBalance - amount);
        return CheckingBalance;
    }

    public int calcSavingWithdraw(int amount) {
        SavingBalance = (SavingBalance - amount);
        return SavingBalance;
    }

    public int calcCheckingDeposit(int amount) {
        CheckingBalance = (CheckingBalance + amount);
        return CheckingBalance;
    }

    public int calcSavingDeposit(int amount) {
        SavingBalance = (SavingBalance + amount);
        return SavingBalance;
    }

    public void getCheckingWithdrawInput() {
        System.out.println("Checking Account Balance: " + moneyFormat.format(CheckingBalance));
        System.out.print("Amount you want to withdraw from Checking Account: ");
        int amount = input.nextInt();
        if ((CheckingBalance - amount) >= 0) {
            calcCheckingWithdraw(amount);
            System.out.println("New Checking Account Balance: " + moneyFormat.format(CheckingBalance));
        } else {
            System.out.println("Balance Cannot be Negative." + "\n");
        }
    }

    public void getCheckingDepositInput() {
        System.out.println("Checking Account Balance: " + moneyFormat.format(CheckingBalance));
        System.out.print("Amount you want to Deposit into Checking Account: ");
        int amount = input.nextInt();
        if (amount > 0) {
            calcCheckingDeposit(amount);
            System.out.println("New Checking Account Balance: " + moneyFormat.format(CheckingBalance));
        } else {
            System.out.println("Deposit amount must be greater than 0." + "\n");
        }
    }

    public void getSavingWithdrawInput() {
        System.out.println("Saving Account Balance: " + moneyFormat.format(SavingBalance));
        System.out.print("Amount you want to withdraw from Saving Account: ");
        int amount = input.nextInt();
        if ((SavingBalance - amount) >= 0) {
            calcSavingWithdraw(amount);
            System.out.println("New Saving Account Balance: " + moneyFormat.format(SavingBalance));
        } else {
            System.out.println("Balance Cannot be Negative." + "\n");
        }
    }

    public void getSavingDepositInput() {
        System.out.println("Saving Account Balance: " + moneyFormat.format(SavingBalance));
        System.out.print("Amount you want to Deposit into Saving Account: ");
        int amount = input.nextInt();
        if (amount > 0) {
            calcSavingDeposit(amount);
            System.out.println("New Saving Account Balance: " + moneyFormat.format(SavingBalance));
        } else {
            System.out.println("Deposit amount must be greater than 0." + "\n");
        }
    }
}
public class OptionMenu extends Account {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$',###,###.##");

    HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();

    public void getLogin() throws IOException {
        int x = 1;
        do {
            try {
                data.put(1000, 9100);
                data.put(2000, 8100);
                data.put(3000, 7100);



                System.out.println("*****Welcome to ATM******");
                System.out.println("ID-1000,PIN-9100");
                System.out.println ("ID-2000,PIN-8100");
                System.out.println("ID-3000,PIN-7100");
                System.out.println("Please enter the valid ID and PIN from above mentioned table ");

                System.out.print("Enter your customer ID: ");
                
                setCustomerNumber(menuInput.nextInt());
                System.out.print("Enter the PIN: ");
                setPinNumber(menuInput.nextInt());

            } catch (Exception e) {
                System.out.println("\n" + "Invalid character. Only numbers are allowed." + "\n");
                x = 2;
            }
            int cn = getCustomerNumber();
            int pn = getPinNumber();
            if (data.containsKey(cn) && data.get(cn) == pn) {
                getAccountType();
            } else {
                System.out.println("\n" + "Wrong customer number or PIN." + "\n");
            }
        } while (x == 1);
    }

    public void getAccountType() {
        System.out.println("Select your account type:");
        System.out.println("1. Current Account");
        System.out.println("2. Saving Account");
        System.out.println("3. Exit");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1:
                getChecking();
                break;
            case 2:
                getSaving();
                break;
            case 3:
                System.out.println("Thank you");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public void getChecking() {
        System.out.println("Current balance");
        System.out.println("1. View Balance");
        System.out.println("2. Withdraw funds");
        System.out.println("3. Deposit funds");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int selection = menuInput.nextInt();
        switch (selection) {
            case 1:
                System.out.println("Checking: " + moneyFormat.format(getCheckingBalance()));
                getAccountType();
                break;
            case 2:
                getCheckingWithdrawInput();
                break;
            case 3:
                getCheckingDepositInput();
                break;
            case 4:
                System.out.println("Thank you");
                break;
            default:
                System.out.println("\n" + "Invalid choice");
                getChecking();
        }
    }

    public void getSaving() {
        System.out.println("Saving balance");
        System.out.println("1. View Balance");
        System.out.println("2. Withdraw funds");
        System.out.println("3. Deposit funds");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int selection = menuInput.nextInt();
        switch (selection) {
            case 1:
                System.out.println("Saving: " + moneyFormat.format(getSavingBalance()));
                getAccountType();
                break;
            case 2:
                getSavingWithdrawInput();
                break;
            case 3:
                getSavingDepositInput();
                break;
            case 4:
                System.out.println("Thank you");
                break;
            default:
                System.out.println("\n" + "Invalid choice");
                getSaving();
        }
    }



public static void main(String[] args) throws IOException {
        OptionMenu optionMenu = new OptionMenu();
        optionMenu.getLogin();
    }

}



















