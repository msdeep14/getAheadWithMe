package abstractfactorypattern;

public class ThreeDSquare implements Shape {
    @Override
    public void create() {
        System.out.println("Create 3D square");
    }
}
