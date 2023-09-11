package Topic9.runners;

public class RunnerList {
      private static final int DEFAULTSIZE = 100;
      private Runner[] runners;
      private int length;
      public RunnerList() {
           this(DEFAULTSIZE);
      }
      public RunnerList(int size) {
           runners = new Runner[size <= 0 ? DEFAULTSIZE : size];
           length = 0;
      }
      public boolean addRunner(String n, int num, double t) {
           if (length == runners.length) {
                return false;           // collection full
          }    
          runners[length++] = new Runner(n, num, t);
          return true;
      }
      public Runner findRunner(int num) {
           for (int i = 0; i < length; i++) {
               if (runners[i].getID() == num) {
                   return runners[i];
               }
           }
           return null;
      }
      public int getLength() {
           return length;
      }
      public Runner findWinner() {
           if (length == 0) {
               return null;     // no Runner objects!
           }
      double bestTime = runners[0].getTime();
      int bestIndex = 0;
      for (int i = 1; i < length; i++ ) {
           if (runners[i].getTime() < bestTime) {
               bestTime = runners[i].getTime();
               bestIndex = i;
          }
      }
      return runners[bestIndex];
   }
}
