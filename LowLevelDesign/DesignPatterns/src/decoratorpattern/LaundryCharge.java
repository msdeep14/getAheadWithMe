package decoratorpattern;

public class LaundryCharge extends RoomChargeDecorator {
    public LaundryCharge(BaseRoomCharge decoratedRoomCharge) {
        super(decoratedRoomCharge);
    }

    @Override
    public String getPriceDescription() {
        return decoratedRoomCharge.getPriceDescription() + " [LaundryCharge - INR 200] ";
    }


    @Override
    public Amount getRoomCharge() {
        Amount amount = decoratedRoomCharge.getRoomCharge();
        double value = amount.getValue() + 200;
        return new Amount(amount.getCurrency(), value);
    }
}
