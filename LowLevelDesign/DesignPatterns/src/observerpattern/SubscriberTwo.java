package observerpattern;

public class SubscriberTwo extends Observer {

    public SubscriberTwo(Subject subject) {
        this.subject = subject;
        this.subject.register(this);
    }
    @Override
    public void update() {
        System.out.println("Received new state by Subscriber Two: " + subject.getState());
    }
}
