package Example_01;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SearchFileRunnable implements Runnable{
    private final BlockingQueue<Path> queue;
    private final String keyword;

    SearchFileRunnable(BlockingQueue<Path> queue, String keyword){
        this.queue=queue;
        this.keyword=keyword;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Path path = this.queue.take();
                if(path==FileTreeWalkerRunnable.END_OF_WORK){
                    this.queue.put(path);
                    break;
                } else {
                    this.search(path);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void search(Path path) {
        try(
            Scanner scanner=new Scanner(path);
        ){
            for(int line_counter=1; scanner.hasNextLine();line_counter++){
                String line= scanner.nextLine();
                if(line.contains(keyword)){
                    System.out.printf("%s: %d\n", path.getFileName(), line_counter);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
