package singletonPattern;

/**
 * Singleton pattern ensures only single object of class is created. This is useful for scenarios such as creating
 * DB connection instance, or a parent entity instance such as ParkingLot.
 *
 *
 * */
public class SingletonImpl {
    //private constructor ensures the class is not instantiated outside this class.
    //Only getInstance() method can be used to get Class object.
    private SingletonImpl() {}

    private static SingletonImpl singleton = null;

    //Declared static so as the method can be called without creating class instance.
    //The object is created when getInstance() is invoked for first time --> referred as lazy initialization.
    public static SingletonImpl getInstance() {
        if(singleton == null) {
            singleton = new SingletonImpl();
        }
        return singleton;
    }
}
