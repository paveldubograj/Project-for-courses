public class ATM {
    private int atmLimit;
    private Card card;

    public int getAtmLimit() {
        return atmLimit;
    }

    public void setAtmLimit(int atmLimit) {
        this.atmLimit = atmLimit;
    }

    public ATM(int atmLimit) {
        this.atmLimit = atmLimit;
        card = null;
    }
    public void setCard(Card card){
        this.card = card;
    }

    public boolean addMoney(int amount){
        if(amount > 1000000) return false;
        else {
            card.setBalance(card.getBalance() + amount);
            return true;
        }
    }

    public int withdrawMoney(int amount){
        if(amount > card.getBalance()) return -1;
        else if(amount > atmLimit) return -2;
        else {
            card.setBalance(card.getBalance() - amount);
            atmLimit -= amount;
            return 0;
        }
    }

    public int balance(){
        return card.getBalance();
    }
}
