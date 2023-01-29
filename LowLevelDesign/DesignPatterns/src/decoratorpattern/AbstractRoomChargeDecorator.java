package decoratorpattern;

public abstract class AbstractRoomChargeDecorator implements BaseRoomCharge {
    protected BaseRoomCharge decoratedRoomCharge;

    public AbstractRoomChargeDecorator(BaseRoomCharge decoratedRoomCharge) {
        this.decoratedRoomCharge = decoratedRoomCharge;
    }

    @Override
    public String getPriceDescription() {
        return "[Extra RoomCharges]";
    }

    public Amount getRoomCharge() {
        return decoratedRoomCharge.getRoomCharge();
    }
}
