package task2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {

	/**
	 * @author ??
	 *
	 */
	static class SinglyLinkedListNode{
		public int data;
		public SinglyLinkedListNode next;

		/**
		 * @param initialData
		 * @param link
		 */
		public SinglyLinkedListNode(int initialData, SinglyLinkedListNode link) {
			data = initialData;
			next = link;
		}
	}

	/**
	 * @author ??
	 *
	 */
	static class SinglyLinkedList{
		public SinglyLinkedListNode head;
		public SinglyLinkedListNode tail;

		/**
		 * 
		 */
		public SinglyLinkedList() {
			head = null;
			tail = null;
		}

		/**
		 * @param nodeData
		 */
		public void insertNodeAtEnd(int nodeData) {
			SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData, null);

			if(head == null)
				head = node;
			else
				tail.next = node;

			tail = node;
		}

		// add additional methods if you need to


		/**
		 * @param head
		 * @return
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
		 * @param head
		 */
		public static void printLinkedList(SinglyLinkedListNode head) {
			// you need to complete this method
			// this method should print a linked list starting from head, value should 
			//      be printed (in console) in a line separated by a space, for example, 10 20 30 40,
			while (head != null) {
                System.out.print(head.data + " ");
                head = head.next;
            }
            System.out.println();

		}

		/**
		 * @param s
		 */
		public void initializeList(Scanner s) {
			// you need to complete this method
			int n = s.nextInt();
            for (int i = 0; i < n; i++) {
                int data = s.nextInt();
                insertNodeAtEnd(data);
			}
		}
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("task2.txt");
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
