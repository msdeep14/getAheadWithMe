package observerpattern;

/**
 * This pattern comes handy when there is need of knowing state of object and get notified as this state changes.
 *
 * "Define a 1:n dependency between objects so that when one object changes state, all its dependents are notified and
 * updated automatically."
 *
 * Observer Pattern has 3 actor classes - Subject, Observer and Client(Observable).
 * Subject - contains methods for attaching/detaching observers to client object.
 *
 * Observer Design Pattern is type of Behavioural Design Pattern.
 *
 * Example - create a topic and all the observers who are subscribed to this topic will get notified if there
 * is any update.
 * */
public class ObserverPatternController {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new SubscriberOne(subject);
        new SubscriberTwo(subject);

        System.out.println("Updating state to 1");
        subject.setState(1);
    }
}
