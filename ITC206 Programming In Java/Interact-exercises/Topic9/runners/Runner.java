package Topic9.runners;

/**
 * A class to represent a runner in a race
 */
public class Runner {
      private String name;
      private int id;       // printed on their shirt
      private double time;  // to complete the race

      public Runner(String n, int num, double t) {
      name = new String( n );
      id = num;
      time = t;
      }

      public int getID() {
      return id;
      }

      public double getTime() {
      return time;
      }

      public String getName() {
      return name;
      }
      
      public void setName(String name) {
        this.name = name;
      }
}
