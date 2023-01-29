package decoratorpattern;

public class Amount {
    Currency currency;
    Double value;

    public Amount(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency=" + currency +
                ", value=" + value +
                '}';
    }
}
