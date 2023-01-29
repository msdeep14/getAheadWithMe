package decoratorpattern;

public class SmokingRoomCharge implements BaseRoomCharge {

    @Override
    public String getPriceDescription() {
        return " [SmokingRoomCharge - INR 6000] ";
    }

    @Override
    public Amount getRoomCharge() {
        return new Amount(Currency.INR, 6000);
    }
}
