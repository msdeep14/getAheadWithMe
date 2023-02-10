package builderPattern;

/**
 * Help setting values in complex object.
 *
 * Example - Object with 10 optional attributes. This way object can be created with any number of attributes.
 * You'll need multiple constructor basis attributes that are passed while initializing new object of this class.
 *
 * Type of Creational Design Pattern.
 * */
public class BuilderPatternController {
    public static void main(String[] args) {
        /**
         * An intermediate class called 'Director' can also be created between Builder class and invocation client(in this case - main() method).
         * If Creation is too complex - example scenarios where RoomBuilder is abstract and provide additional method overrides such as
         * Builder as per room Type, etc.
         * */
        Room myRoom = new RoomBuilder()
                .setRoomNumber("123")
                .setRoomPrice("price123")
                .setRoomType("MehangaWala")
                .build();
        System.out.println(myRoom);
    }
}
