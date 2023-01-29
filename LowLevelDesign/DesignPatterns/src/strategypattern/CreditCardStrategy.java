package strategypattern;

public class CreditCardStrategy implements PaymentStrategy {
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String cardHolderName;

    public CreditCardStrategy(String cardNumber, String cvv, String expiryDate, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
    }


    @Override
    public void pay(double amount) {
        System.out.println("Proceed to pay with credit card");
    }
}
