package Example_01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class CharGeneratorClientBlocking {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 19000;

        try {
            SocketAddress address = new InetSocketAddress(host, port);
            SocketChannel client = SocketChannel.open(address);

            ByteBuffer buffer = ByteBuffer.allocate(74);
            WritableByteChannel out = Channels.newChannel(System.out);

            while (client.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }

        } catch (IOException ex) {
            ex.printStackTrace( );
        }

    }
}
