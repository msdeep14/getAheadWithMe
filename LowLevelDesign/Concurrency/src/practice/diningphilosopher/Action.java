package practice.diningphilosopher;

public enum Action {
    EATING, //
    THINKING,
    HUNGRY // As philosopher is hungry, tries to check if left and right forks are available, if available then change state to EATING
}
