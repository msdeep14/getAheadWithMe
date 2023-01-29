package decoratorpattern;

public class RoomChargeDecorator extends AbstractRoomChargeDecorator {
    public RoomChargeDecorator(BaseRoomCharge decoratedRoomCharge) {
        super(decoratedRoomCharge);
    }
}
