package nullobjectpattern;

public class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("Dog eating food");
    }

    @Override
    public String getAnimalId() {
        return "dog123";
    }
}
