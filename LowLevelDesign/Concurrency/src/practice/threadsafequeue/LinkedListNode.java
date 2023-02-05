package practice.threadsafequeue;

public class LinkedListNode<T> {

    Node head;
    Node tail;
    public static class Node<T> {
        T value;
        Node next;
        Node prev;

        Node(T data) {
            value = data;
            next = null;
            prev = null;
        }
    }

    public void addNode(T value) {
        if(head == null) {
            head = new Node(value);
            tail = head;
        } else {
            Node newNode = new Node(value);
            Node temp = tail;
            //tail.next = temp;
        }
    }

    public void removeNode() {

    }
}
