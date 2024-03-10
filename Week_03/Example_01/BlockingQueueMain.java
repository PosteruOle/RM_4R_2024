package Example_01;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueMain {
    private static final int FILE_QUEUE_SIZE=10;
    private static final int NUMBER_OF_THREADS=5;
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter the name of base directory: ");
        String base_directory= scanner.nextLine();

        System.out.println("Enter the keyword:");
        String keyword= scanner.nextLine();

        scanner.close();

        // We are implementing a variant of the popular design pattern thread pool (or replicated workers):
        // https://en.wikipedia.org/wiki/Thread_pool

        /*
        Design patterns are reusable solutions to common problems in software design.
        Here are some of the most famous design patterns:
        1) Singleton Pattern:
        Ensures a class has only one instance and provides a global point of access to it.

        2) Factory Method Pattern:
        Defines an interface for creating an object, but leaves the choice of its type to the subclasses, creating instances of a class.

        3) Abstract Factory Pattern:
        Provides an interface for creating families of related or dependent objects without specifying their concrete classes.

        4) Builder Pattern:
        Separates the construction of a complex object from its representation, allowing the same construction process to create various representations.

        5) Strategy Pattern:
        Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from the clients that use it.
        */

        BlockingQueue<Path> fileQueue=new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
        FileTreeWalkerRunnable ftw=new FileTreeWalkerRunnable(fileQueue, Paths.get(base_directory));
        new Thread(ftw).start();

        for(int i=1;i<=NUMBER_OF_THREADS;i++){
            new Thread(new SearchFileRunnable(fileQueue, keyword)).start();
        }

    }
}
