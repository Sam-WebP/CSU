public class LinkedList {
  private Node head;

  public void traverse() {
    Node curr = head;
    while (curr != null) {
      System.out.print(curr.data + " ");
      curr = curr.next;
    }
    System.out.println();
  }

  public void insert(int data, int pos) {
    Node toAdd = new Node(data);
    if (pos == 0) {
      toAdd.next = head;
      head = toAdd;
      return;
    }
    Node prev = head;
    for (int i = 0; i < pos - 1; i++) {
      if (prev == null) {
        throw new IndexOutOfBoundsException("Invalid position");
      }
      prev = prev.next;
    }
    toAdd.next = prev.next;
    prev.next = toAdd;
  }

  public void delete(int pos) {
    if (head == null) {
      return;
    }
    if (pos == 0) {
      head = head.next;
      return;
    }
    Node prev = head;
    for (int i = 0; i < pos - 1; i++) {
      if (prev.next == null) {
        throw new IndexOutOfBoundsException("Invalid position");
      }
      prev = prev.next;
    }
    if (prev.next == null) {
      throw new IndexOutOfBoundsException("Invalid position");
    }
    prev.next = prev.next.next;
  }
}