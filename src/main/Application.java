package main;

import java.util.HashMap;

public class Application {

    private HashMap<Integer, Integer> solutions;
    private int lowerBorder;
    private int upperBorder;

    public Application(int lowerBorder, int upperBorder) {
        solutions = new HashMap<>();
        this.lowerBorder = lowerBorder;
        this.upperBorder = upperBorder;
    }

    /**
     * Starts the application for the interval [1; 100]
     *
     * @param args system arguments
     */
    public static void main(String... args) {
        Application app = new Application(1, 100);
        app.execute();
    }

    /**
     * Assigns intervals that decrease in size for bigger number values in order to distribute the workload
     */
    private void execute() {
        Thread task01 = new Thread(new Task(1, this, lowerBorder, upperBorder / 2));
        Thread task02 = new Thread(new Task(2, this, upperBorder / 2, (3 * upperBorder) / 4));
        Thread task03 = new Thread(new Task(3, this, (3 * upperBorder) / 4, (93 * upperBorder) / 100));
        Thread task04 = new Thread(new Task(4, this, (93 * upperBorder) / 100, upperBorder + 1));

        System.out.println();

        long runtimeStart = System.currentTimeMillis();

        TaskRecorder.instance.startup();
        TaskRecorder.instance.init();

        task01.start();
        task02.start();
        task03.start();
        task04.start();

        try {
            task01.join();
            task02.join();
            task03.join();
            task04.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        TaskRecorder.instance.shutdown();

        System.out.println(solutions);

        System.out.println("runtime (s)        : " + (System.currentTimeMillis() - runtimeStart) / 1000);
    }

    void addSolution(String type, int index, int number) {
        solutions.put(index, number);
        if (Configuration.instance.persistData) {
            TaskRecorder.instance.insert(type, index, number);
        }
    }
}
