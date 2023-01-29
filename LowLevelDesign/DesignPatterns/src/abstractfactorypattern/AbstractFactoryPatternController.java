package abstractfactorypattern;

public class AbstractFactoryPatternController {
    public static void main(String[] args) {
        MyAbstractFactory abstractFactory = MyFactoryProducer.getFactory(true);
        Shape shape1 = abstractFactory.getShape("RECTANGLE");
        shape1.create();

        MyAbstractFactory abstractFactory1 = MyFactoryProducer.getFactory(false);
        Shape shape2 = abstractFactory1.getShape("SQUARE");
        shape2.create();
    }
}
