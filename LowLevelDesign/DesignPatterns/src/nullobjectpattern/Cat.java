package nullobjectpattern;

public class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("Cat eating food");
    }

    @Override
    public String getAnimalId() {
        return "CAT123";
    }
}
