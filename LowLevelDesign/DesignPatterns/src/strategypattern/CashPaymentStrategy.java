package strategypattern;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Proceed with cash payment");
    }
}
