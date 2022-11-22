public class Card {
    private final String cardNumber;
    private final String pinCode;
    private int balance;
    private String blockingDate;

    public String getBlockingDate() {
        return blockingDate;
    }

    public void setBlockingDate(String blockingDate) {
        this.blockingDate = blockingDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Card(String cardNumber, String pinCode, int balance, String blockingDate){
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.balance = balance;
        this.blockingDate = blockingDate;
    }

    @Override
    public String toString(){
        return String.format("%s %s %d %s", cardNumber, pinCode, balance, blockingDate);
    }
}
