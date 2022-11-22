import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public final class Bank {
    private static ArrayList<Card> cards = new ArrayList<>();

    public static void printList(){
        for (var card: cards) {
            System.out.println(card.toString());
        }
    }
    public static boolean isBlocked(String cardNumber){
        Card info =  cards.stream()
                .filter(card -> cardNumber.equals(card.getCardNumber()))
                .findFirst().orElse(null);
        if(info == null) throw new IllegalArgumentException("There is no card with such number");
        if(info.getBlockingDate().equals("Notblocked")) return false;
        if(LocalDateTime.now().isAfter(LocalDateTime.parse(info.getBlockingDate()).plusDays(1))) {
            info.setBlockingDate("Notblocked");
            return false;
        }
        return true;
    }

    public static boolean validateCardNumber(String number){
        if(number.matches("(([0-9]{4})-([0-9]{4})-([0-9]{4})-([0-9]{4}))")) {
            if (cards.stream()
                    .filter(card -> number.equals(card.getCardNumber()))
                    .findFirst()
                    .orElse(null) != null) {
                return true;
            }
        }
        return false;
    }

    public static Card validatePinCode(String cardNumber, String pinCode){
        Card info = cards.stream()
                .filter(card -> cardNumber.equals(card.getCardNumber()))
                .findFirst().orElse(null);
        if(info != null && info.getPinCode().equals(pinCode)) return info;
        return null;
    }

    public static void blockCard(String cardNumber){
        Card info = cards.stream()
                .filter(card -> cardNumber.equals(card.getCardNumber()))
                .findFirst().orElse(null);
        if(info != null) info.setBlockingDate(LocalDateTime.now().toString());
    }

    public static void readCardsBase(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/CardsBase.txt"));
            String line = reader.readLine();
            cards.clear();
            while (line != null) {
                String[] parts = line.split(" ");
                cards.add(new Card(parts[0].trim(), parts[1].trim(),
                        Integer.parseInt(parts[2].trim()), parts[3].trim()));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCardsBase(){
        try(FileWriter writer = new FileWriter("src/CardsBase.txt", false))
        {
            for (Card card: cards) {
               writer.write(card.toString() + '\n');
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private Bank(){}

}
