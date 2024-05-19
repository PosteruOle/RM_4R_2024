package Example_02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

public class IntegerGeneratorClient {
    public static void main(String[] args) {
        InetSocketAddress addr = new InetSocketAddress("localhost", IntegerGeneratorServer.DEFAULT_PORT);

        try (SocketChannel client = SocketChannel.open(addr)) {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            IntBuffer view = buffer.asIntBuffer();

            for (int expected = 0; ; expected++) {
                while (buffer.hasRemaining())
                    client.read(buffer);

                int received = view.get();

                // Prepare for next read() call
                buffer.clear();

                // Prepare for next get() call
                view.rewind();

                if (received != expected) {
                    System.err.println("Expected: " + expected + "; got: " + received);
                    break;
                }

                System.out.println(received);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
