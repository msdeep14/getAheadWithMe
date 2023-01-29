package observerpattern;

public class SubscriberOne extends Observer {

    public SubscriberOne(Subject subject) {
        this.subject = subject;
        this.subject.register(this);
    }
    @Override
    public void update() {
        System.out.println("Received new state by Subscriber One: " + subject.getState());
    }
}
