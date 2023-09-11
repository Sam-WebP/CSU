package Topic9.runners;

public class TestRunnerClasses {
    public static void main(String[] args) {
        // Create a RunnerList
        RunnerList runnerList = new RunnerList();

        // Add some runners
        runnerList.addRunner("Alice", 1, 12.5);
        runnerList.addRunner("Bob", 2, 10.3);
        runnerList.addRunner("Charlie", 3, 15.7);
        runnerList.addRunner("David", 4, 9.8);

        // Print the number of runners
        System.out.println("Total runners: " + runnerList.getLength());

        // Search for a runner by ID
        Runner foundRunner = runnerList.findRunner(2);
        if (foundRunner != null) {
            System.out.println("Found runner with ID 2: " + foundRunner.getName());
        } else {
            System.out.println("Runner with ID 2 not found.");
        }

        // Find the winner
        Runner winner = runnerList.findWinner();
        if (winner != null) {
            System.out.println("Winner is " + winner.getName() + " with a time of " + winner.getTime());
        } else {
            System.out.println("No runners in the list.");
        }

        Runner test1 = new Runner("Sam", 10, 2.5);
        System.out.println(test1.getName());
        System.out.println("Now the name is going to be changed.");
        test1.setName("Samantha");
        System.out.println(test1.getName());

    }
}

