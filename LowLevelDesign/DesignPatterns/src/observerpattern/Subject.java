package observerpattern;

import java.util.ArrayList;
import java.util.List;

public class Subject extends Observer {
    private List<Observer> observerList;
    private int state;

    public Subject() {
        observerList = new ArrayList<>();
    }
    @Override
    public void update() {

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    public void register(Observer observer) {
        observerList.add(observer);
    }

    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
        for(Observer observer : observerList) {
            observer.update();
        }
    }
}
