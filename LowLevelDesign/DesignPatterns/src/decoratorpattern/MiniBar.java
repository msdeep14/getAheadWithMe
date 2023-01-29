package decoratorpattern;

public class MiniBar extends RoomChargeDecorator {
    public MiniBar(BaseRoomCharge decoratedRoomCharge) {
        super(decoratedRoomCharge);
    }

    @Override
    public String getPriceDescription() {
        return decoratedRoomCharge.getPriceDescription() + " [MiniBarRoomCharge - INR 500] ";
    }


    @Override
    public Amount getRoomCharge() {
        Amount amount = decoratedRoomCharge.getRoomCharge();
        double value = amount.getValue() + 500;
        return new Amount(amount.getCurrency(), value);
    }
}
