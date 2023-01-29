package abstractfactorypattern;

public class MyFactoryProducer {
    public static MyAbstractFactory getFactory(boolean is3D) {
        if(is3D) {
            return new MyThreeDShapeFactory();
        } else {
            return new MyFactory();
        }
    }
}
