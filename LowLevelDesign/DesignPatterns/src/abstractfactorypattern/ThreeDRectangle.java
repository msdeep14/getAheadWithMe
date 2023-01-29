package abstractfactorypattern;

public class ThreeDRectangle implements Shape {
    @Override
    public void create() {
        System.out.println("create 3D rectangle");
    }
}
