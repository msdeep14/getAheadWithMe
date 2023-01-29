package decoratorpattern;

public class NonSmokingRoomCharge implements BaseRoomCharge {

    @Override
    public String getPriceDescription() {
        return " [NonSmokingRoomCharge INR 5000] ";
    }

    @Override
    public Amount getRoomCharge() {
        return new Amount(Currency.INR, 5000);
    }
}
