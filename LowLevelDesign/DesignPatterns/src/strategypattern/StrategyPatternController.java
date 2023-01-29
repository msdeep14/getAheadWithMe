package strategypattern;

/**
 * Strategy Pattern is used when there are multiple ways/algorithms/strategies to execute specific task and client can
 * decide the implementation at runtime.
 *
 * Strategy Design Pattern is type of Behavioural Design Pattern.
 *
 * We'll build this example on top of what we discussed in Decorator Pattern. You figured out total cost of customer's stay
 * at Hotel, now customer has to pay the amount.
 * Now customer can choose the mode of Payment - example credit card, debit card, UPI, cash etc.
 *
 * We've all the strategies in place and as per customer's choice, receptionist can invoke the payment strategy.
 * */
public class StrategyPatternController {
    private static void makePayment(PaymentType paymentType, double amount) {
        switch (paymentType) {
            case CREDIT_CARD:
                //should be part of customer's makePayment method
                new CreditCardStrategy("","","","").pay(amount);
                break;
            case CASH:
                new CashPaymentStrategy().pay(amount);
                break;
            default:
                throw new RuntimeException("Payment Method is required to process payment");
        }
    }
    public static void main(String[] args) {

        //room charges = INR 6300
        makePayment(PaymentType.CREDIT_CARD, 6300);

        makePayment(PaymentType.CASH, 6700);

    }
}
