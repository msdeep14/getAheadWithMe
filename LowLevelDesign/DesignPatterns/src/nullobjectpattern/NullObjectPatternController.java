package nullobjectpattern;

public class NullObjectPatternController {

    private static void getAnimalDetails(Animal animal) {
        //There is no requirement to check (if(animal!=null)), as handled explicitly.
        System.out.println("AnimalId: " + animal.getAnimalId());
    }
    public static void main(String[] args) {
        Animal animal = AnimalProvider.getAnimalInstance(AnimalType.CAT);
        animal.eat();
        getAnimalDetails(animal);

        Animal noAnimal = AnimalProvider.getAnimalInstance(AnimalType.DEFAULT);
        noAnimal.eat();
        getAnimalDetails(noAnimal);
    }
}
