public class Main {
  public static void main(String[] args) {
    LinkedList linkedList = new LinkedList();

    // Test case 1: Insert elements at different positions
    linkedList.insert(10, 0);
    linkedList.insert(20, 1);
    linkedList.insert(30, 2);
    linkedList.insert(15, 1);
    System.out.print("Linked list after insertion: ");
    linkedList.traverse();

    // Test case 2: Delete elements from different positions
    linkedList.delete(2);
    System.out.print("Linked list after deleting element at index 2: ");
    linkedList.traverse();

    linkedList.delete(0);
    System.out.print("Linked list after deleting element at index 0: ");
    linkedList.traverse();

    // Test case 3: Insert and delete elements at boundary positions
    linkedList.insert(5, 0);
    linkedList.insert(40, 3);
    System.out.print("Linked list after insertion at boundary positions: ");
    linkedList.traverse();

    linkedList.delete(3);
    System.out.print("Linked list after deleting element at last index: ");
    linkedList.traverse();

    // Test case 4: Delete from an empty linked list
    LinkedList emptyList = new LinkedList();
    emptyList.delete(0);
    System.out.print("Empty linked list after deletion: ");
    emptyList.traverse();
  }
}