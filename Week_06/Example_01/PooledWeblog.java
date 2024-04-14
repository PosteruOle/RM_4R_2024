package Example_01;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// This class has the task to process the log file line-by-line and use
// threads which will process one line at a time. The log file is provided
// via InputStream and the output will be sent to a PrintStream
final class PooledWeblog {
    private BufferedReader in;
    private BufferedWriter out;
    private int numOfThreads;
    private boolean finished;
    private final List<String> entries = Collections.synchronizedList(new LinkedList<>());

    PooledWeblog(FileInputStream in, PrintStream out, int threads) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new BufferedWriter(new OutputStreamWriter(out));
        this.numOfThreads = threads;
    }

    void processLogFile() {

        // Starting the lookup threads
        for (int i = 0; i < this.numOfThreads; i++) {
            Thread t = new Thread(new LookupRunnable(this));
            t.start();
        }

        try {
            // Reading the log file line by line
            for (String entry = in.readLine(); entry != null; entry = in.readLine()) {
                // Since log file is very large and because threads will need more time
                // to do their logic than it takes to read a line, the list will grow
                // in size. We do not want for our list to too become large so we wait
                // a bit if the list size is greater than threads amount so threads
                // can catch up
                while (this.entries.size() > this.numOfThreads) {
                    try {
                        Thread.sleep((long) (1000.0 / this.numOfThreads));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // We can operate on synchronized collections using a synchronized block
                synchronized (this.entries) {
                    this.entries.add(0, entry);

                    // Threads will wait if the list is empty, so after each addition
                    // we will notify them. In order to wait/notify we MUST be inside
                    // a synchronized block (similar to holding locks when awaiting or
                    // signalling a condition
                    this.entries.notifyAll();
                }

                // Yielding means that the thread is not doing anything particularly
                // important and if any other threads or processes need to be run,
                // they should run. Otherwise, the current thread will continue
                Thread.yield();
            }

            // Signalize threads that the work is finished
            this.finished = true;
            System.err.println("Work finished! Signalling...");
            synchronized (this.entries) {
                this.entries.notifyAll();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void log(String entry) throws IOException {
        this.out.write(entry);
        this.out.newLine();
        this.out.flush();
    }

    List<String> getEntries() {
        return this.entries;
    }

    boolean isFinished() {
        return this.finished;
    }
}
