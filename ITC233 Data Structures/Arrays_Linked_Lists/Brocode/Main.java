import java.util.LinkedList;
import java.util.ArrayList;

  /* 
  
  LinkedList:
    - Notes are in 2 parts (data + address)
    - Notes are in non-consecutive memory locations
    - Elements are linked using pointers

  Advantages:
    1. Dynamic Data Structure (allocates needed memory while running)
    2. Insertion and deletion of Nodes is easy. O(1)
    3. No/Low memory waste

  Disadvantages:
    1. Greater memory usage (additional pointer)
    2. No random access of elements (no index [i])
    3. Accessing/searching elements is more time consuming. O(n)

  Uses: 
    1. Implement Stacks/Queues
    2. GPS navigation
    3. Music playlist

   */

public class Main {
  
  public static void main(String[] args) {

    // LinkedList<String> linkedList = new LinkedList<String>();

    // linkedList.push("A");
    // linkedList.push("B");
    // linkedList.push("C");
    // linkedList.push("D");
    // linkedList.push("F");
    // linkedList.pop();

    // linkedList.offer("A");
    // linkedList.offer("B");
    // linkedList.offer("C");
    // linkedList.offer("D");
    // linkedList.offer("F");
    // // linkedList.poll();

    LinkedList<Integer> linkedList = new LinkedList<Integer>();
    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    long startTime;
    long endTime;
    long elapsedTime;

    for(int i = 0; i < 1000000; i++) {
      linkedList.add(i);
      arrayList.add(i);
    }

    startTime = System.nanoTime();
    //linkedList.get(0); //16792
    //linkedList.get(500000); //3293148
    //linkedList.get(999999); //14107
    //linkedList.remove(0); //15258
    //linkedList.remove(500000); //3729166
    linkedList.remove(999999); //16441

    endTime = System.nanoTime();

    elapsedTime = endTime - startTime;

    System.out.println("LinkedList: \t" + elapsedTime + " ns");

   /// ********************* ArrayList ********************* ///

   startTime = System.nanoTime();
   //arrayList.get(0); //5320
   //arrayList.get(500000); //4398
   //arrayList.get(999999); //6533
   //arrayList.remove(0); //279174
   //arrayList.remove(500000); //167684
   arrayList.remove(999999); //5190

   endTime = System.nanoTime();

   elapsedTime = endTime - startTime;

   System.out.println("ArrayList: \t" + elapsedTime + " ns");


    }

  

}
