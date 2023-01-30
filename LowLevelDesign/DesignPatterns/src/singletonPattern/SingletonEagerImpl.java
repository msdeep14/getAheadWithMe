package singletonPattern;

public class SingletonEagerImpl {

    //This type of singleton instantiation is called as eager instantiation.
    //Eager is opposite of lazy initialization.
    //Eager - initialize object when it is created.
    //lazy - initialize object when it is first accessed.

    //JVM executes static initializer when class is loaded so it will be thread safe.
    //This remains throughout the program execution, so we don't get benefits such as lazy initialization.
    //Preferred way if we require instance throughout execution.
    private static SingletonEagerImpl singleton = new SingletonEagerImpl();
    private SingletonEagerImpl() {}

    public static SingletonEagerImpl getInstance() {
        return singleton;
    }
}
