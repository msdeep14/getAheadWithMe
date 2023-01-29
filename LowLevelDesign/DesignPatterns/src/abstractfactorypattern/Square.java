package abstractfactorypattern;

public class Square implements Shape {
    @Override
    public void create() {
        System.out.println("create simple square");
    }
}
