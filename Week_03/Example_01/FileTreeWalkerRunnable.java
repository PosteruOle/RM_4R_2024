package Example_01;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;

public class FileTreeWalkerRunnable implements Runnable{
    public static final Path END_OF_WORK= Paths.get("");
    private final BlockingQueue<Path> queue;
    private final Path starting_directory;

    FileTreeWalkerRunnable(BlockingQueue<Path> queue, Path starting_directory){
        this.queue=queue;
        this.starting_directory=starting_directory;
    }
    @Override
    public void run() {
        try{
            walk(this.starting_directory);
            this.queue.put(END_OF_WORK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void walk(Path starting_directory) {
        try(
            DirectoryStream<Path> directoryStream= Files.newDirectoryStream(starting_directory)
        ){
            for(Path path: directoryStream){
                if(Files.isDirectory(path)){
                    walk(path);
                } else {
                    this.queue.put(path);
                }
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
