public class Problem1Main {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class SinglyLinkedList {
        Node head;
        Node tail;

        void addFirst(int token) {
            Node newNode = new Node(token);
            newNode.next = head;
            head = newNode;

            if (tail == null) {
                tail = newNode;
            }
        }

        void addLast(int token) {
            Node newNode = new Node(token);

            if (head == null) {
                head = tail = newNode;
                return;
            }

            tail.next = newNode;
            tail = newNode;
        }

        void insertAt(int index, int token) {
            if (index == 0) {
                addFirst(token);
                return;
            }

            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            Node newNode = new Node(token);
            newNode.next = current.next;
            current.next = newNode;

            if (newNode.next == null) {
                tail = newNode;
            }
        }

        void printList() {
            Node current = head;

            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }

            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.printList();
    }
}