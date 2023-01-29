package abstractfactorypattern;

public class Rectangle implements Shape {
    @Override
    public void create() {
        System.out.println("create simple rectangle");
    }
}
