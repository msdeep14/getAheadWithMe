package decoratorpattern;

public class InRoomDiningFood extends RoomChargeDecorator {
    public InRoomDiningFood(BaseRoomCharge decoratedRoomCharge) {
        super(decoratedRoomCharge);
    }

    @Override
    public String getPriceDescription() {
        return decoratedRoomCharge.getPriceDescription() + " [InRoomDiningFood - INR 600] ";
    }


    @Override
    public Amount getRoomCharge() {
        Amount amount = decoratedRoomCharge.getRoomCharge();
        double value = amount.getValue() + 600;
        return new Amount(amount.getCurrency(), value);
    }
}
