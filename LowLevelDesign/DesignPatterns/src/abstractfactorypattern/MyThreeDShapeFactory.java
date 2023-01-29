package abstractfactorypattern;

public class MyThreeDShapeFactory extends MyAbstractFactory {
    @Override
    public Shape getShape(String shapeType) {
        switch (shapeType) {
            case "RECTANGLE":
                return new ThreeDRectangle();
            case "SQUARE":
                return new ThreeDSquare();
            default:
                return null;
        }
    }
}
