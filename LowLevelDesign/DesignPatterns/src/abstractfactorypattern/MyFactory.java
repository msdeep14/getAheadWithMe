package abstractfactorypattern;

public class MyFactory extends MyAbstractFactory {
    @Override
    public Shape getShape(String shapeType) {
        switch (shapeType) {
            case "RECTANGLE":
                return new Rectangle();
            case "SQUARE":
                return new Square();
            default:
                return null;
        }
    }
}
