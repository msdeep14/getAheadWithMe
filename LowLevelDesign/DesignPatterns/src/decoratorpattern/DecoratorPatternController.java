package decoratorpattern;

/**
 * Decorator pattern allows to add new functionality to existing object without altering its structure
 * at runtime.
 *
 * Decorator Design pattern is Structural Design Pattern.
 * */
public class DecoratorPatternController {

    public static void main(String[] args) {

        //get a Smoking Room with minibar and laundry = 6000 + 500 + 200
        BaseRoomCharge charge1 = new RoomChargeDecorator(new LaundryCharge(new MiniBar(new SmokingRoomCharge())));
        System.out.println(charge1.getPriceDescription());
        System.out.println(charge1.getRoomCharge());

        //get a non-smoking room with laundry, minibar and inRoomDiningFood = 5000 + 200 + 500 + 600
        BaseRoomCharge charge2 = new RoomChargeDecorator(new InRoomDiningFood(new MiniBar(new LaundryCharge(new NonSmokingRoomCharge()))));
        System.out.println(charge2.getPriceDescription());
        System.out.println(charge2.getRoomCharge());

        //get a smoking room with laundry, InRoomDiningFood = 5000 + 200 + 600
        BaseRoomCharge charge3 = new SmokingRoomCharge();
        charge3 = new LaundryCharge(charge3);
        charge3 = new InRoomDiningFood(charge3);
        System.out.println(charge3.getPriceDescription());
        System.out.println(charge3.getRoomCharge());
    }
}
