import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {
    public static void start(){
        boolean clearcard = false;
        int op = 0;
        int amount = 0;
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("Enter amount of money in ATM");
            try {
                if(op != 0)in.next();
                amount = in.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Enter correct number");
                op = 1;
                continue;
            }
            break;
        }
        ATM atm = new ATM(amount);
        while(!clearcard) {
            Bank.readCardsBase();
            clearcard = check(atm);
        }
            while(true) {
                System.out.println("Choose operation:\n1.Add money to card;\n2.Withdraw money from card;\n3.Show balance;\n4.End.");
                try {
                    op = in.nextInt();
                }
                catch (InputMismatchException ex){
                    System.out.println("Enter correct number");
                    continue;
                }
                if (op == 1)
                {
                    System.out.println("Enter amount to add");
                    amount = in.nextInt();
                    if(atm.addMoney(amount)) System.out.println("Money were added");
                    else System.out.println("You can not put more than 1 000 000 on your account");
                }
                else if (op == 2)
                {
                    System.out.println("Enter amount to withdraw");
                    amount = in.nextInt();
                    int check = atm.withdrawMoney(amount);
                    if(check == -1) System.out.println("You do not have enough money. Your balance: " + atm.balance());
                    else if(check == -2) System.out.println("There is not enough money in the ATM. Money in the ATM: " + atm.getAtmLimit());
                    else System.out.println("Withrawed");
                }
                else if (op == 3)
                {
                    System.out.println("Balance: " + atm.balance());
                }
                else if (op == 4)
                {
                    break;
                }
                else System.out.println("Choose correct operation");
            }
        Bank.writeCardsBase();
    }
    public static boolean check(ATM atm){
        Scanner in = new Scanner(System.in);
        boolean clearcard = false;
        System.out.println("Enter card number:");
        String cardNumber = in.next();
        if(Bank.validateCardNumber(cardNumber)){
            if(!Bank.isBlocked(cardNumber)) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Enter pin code:");
                    String pinCode = in.next();
                    Card testCard = Bank.validatePinCode(cardNumber, pinCode);
                    if (testCard != null) {
                        atm.setCard(testCard);
                        clearcard = true;
                        break;
                    }
                }
                if(!clearcard) {
                    System.out.println("Card was blocked " + new Date());
                    Bank.blockCard(cardNumber);
                    clearcard = false;
                    Bank.writeCardsBase();
                }
            }
            else {
                System.out.println("This card is blocked");
                clearcard = false;
            }
        }
        else {
            System.out.println("There is no card with such number");
            clearcard = false;
        }
        return clearcard;
    }
}
