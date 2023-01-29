package nullobjectpattern;

public class AnimalProvider {

    /**
     * Example of Factory Design Pattern.
     *
     * Object is returned basis given condition, for example in below scenario, instance is returned
     * basis animalType.
     * */
    public static Animal getAnimalInstance(AnimalType animalType) {
        switch (animalType) {
            case CAT:
                return new Cat();
            case DOG:
                return new Dog();
            default:
                return new NoAnimal();
        }
    }
}
