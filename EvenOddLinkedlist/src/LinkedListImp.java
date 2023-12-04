public class LinkedListImp<T extends Comparable<T>> {
    private Node<T> head;

    public LinkedListImp() {
        this.head = null;
    }
    public void addInto(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println();
    }

    public void evenOddSort() {
        head = mergeSortInPlace(head);
    }
    private Node<T> mergeSortInPlace(Node<T> start) {
        if (start == null || start.next == null) {
            return start;
        }
        Node<T> middle = getMiddle(start);
        Node<T> nextToMiddle = middle.next;
        middle.next = null;

        Node<T> left = mergeSortInPlace(start);
        Node<T> right = mergeSortInPlace(nextToMiddle);

        return mergeInPlace(left, right);
    }

    private Node<T> getMiddle(Node<T> start) {
        if (start == null) {
            return null;
        }
        Node<T> slow = start;
        Node<T> fast = start.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    private Node<T> mergeInPlace(Node<T> left, Node<T> right) {
        Node<T> dummy = new Node<>(null);
        Node<T> current = dummy;

        while (left != null && right != null) {
            if (isEven(left.data) && isEven(right.data)) {
                if (left.data.compareTo(right.data) <= 0) {
                    current.next = left;
                    left = left.next;
                } else {
                    current.next = right;
                    right = right.next;
                }
            } else if (isEven(left.data)) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }

            current = current.next;
        }

        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }

        return dummy.next;
    }

    private boolean isEven(T value) {
        if (value instanceof Integer) {
            return ((Integer) value) % 2 == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListImp<Integer> linkedlist = new LinkedListImp<>();
        linkedlist.addInto(17);
        linkedlist.addInto(15);
        linkedlist.addInto(8);
        linkedlist.addInto(9);
        linkedlist.addInto(2);
        linkedlist.addInto(4);
        linkedlist.addInto(6);

        System.out.println("My linkedlist before sorting is : ");
        linkedlist.printList();
        linkedlist.evenOddSort();
        System.out.println("Sorted linkedlist with even nodes first:");
        linkedlist.printList();

    }
}
