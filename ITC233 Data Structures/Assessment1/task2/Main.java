package task2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Main class demonstrates operations on a singly linked list,
 * which includes insertion, reversal, and printing of the list.
 */

public class Main {

	/**
     * The SinglyLinkedListNode class represents a node in a singly linked list.
     */
	static class SinglyLinkedListNode{
		public int data;
		public SinglyLinkedListNode next;

		/**
         * Constructs a new SinglyLinkedListNode with the given data and next node.
         *
         * @param initialData the data value of the node
         * @param link        the reference to the next node
         */
		public SinglyLinkedListNode(int initialData, SinglyLinkedListNode link) {
			data = initialData;
			next = link;
		}
	}

	/**
     * The SinglyLinkedList class represents a singly linked list.
     */
	static class SinglyLinkedList{
		public SinglyLinkedListNode head;
		public SinglyLinkedListNode tail;

		/**
   		 * Constructor to create an empty singly linked list.
   		 */
		public SinglyLinkedList() {
			head = null;
			tail = null;
		}

		/**
		 * Inserts a new node containing the given data at the end of the linked list.
		 *
		 * @param nodeData the data to be inserted
		 */
		public void insertNodeAtEnd(int nodeData) {
			SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData, null);

			if(head == null)
				head = node;
			else
				tail.next = node;

			tail = node;
		}

		/**
         * Reverses the singly linked list.
         *
         * @param head the head of the list
         * @return the head of the reversed list
         */
		public static SinglyLinkedListNode reverse (SinglyLinkedListNode head) {
			SinglyLinkedListNode prev = null;
            SinglyLinkedListNode cur = head;
            SinglyLinkedListNode next = null;

            while (cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            return prev;
		}

		/**
		 * Prints the data of each node in the linked list.
		 *
		 * @param head the head node of the linked list to print
		 */
		public static void printLinkedList(SinglyLinkedListNode head) {
			while (head != null) {
                System.out.print(head.data + " ");
                head = head.next;
            }
            System.out.println();
		}

		/**
         * Initialises the singly linked list with data read from a scanner.
         *
         * @param s the scanner to read data from
         */
		public void initializeList(Scanner s) {
			int n = s.nextInt();
            for (int i = 0; i < n; i++) {
                int data = s.nextInt();
                insertNodeAtEnd(data);
			}
		}
	}

	/**
     * The main method is the entry point of the program.
     *
     * @param args command-line arguments (not used)
     */
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("task2/task2.txt");
		Scanner sc = new Scanner(file); //read data from a file
		//Scanner sc = new Scanner(System.in); //read data from console

		int t = sc.nextInt(); //t is the number of test cases
		for(int i=0; i< t; ++i) {
			SinglyLinkedList myList = new SinglyLinkedList();
			myList.initializeList(sc); // read data from a file and initialize list
			SinglyLinkedListNode headOfReverseList = SinglyLinkedList.reverse(myList.head); //reverse myList
			SinglyLinkedList.printLinkedList(headOfReverseList); //print the reverse list
		}
	}

}
