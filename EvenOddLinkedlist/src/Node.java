// Node.java
public class Node<T extends Comparable<T>> {
    T data;
    Node<T> next;

    // Constructor
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
