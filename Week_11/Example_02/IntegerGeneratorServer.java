package Example_02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class IntegerGeneratorServer {
    public static int DEFAULT_PORT = 9595;
    public static void main(String[] args) {
        System.out.println("Listening for connections on port " + DEFAULT_PORT);
        try (
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();
        ) {

            serverChannel.bind(new InetSocketAddress(DEFAULT_PORT));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                selector.select();

                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    try {
                        if (key.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel)key.channel();
                            SocketChannel client = server.accept();
                            System.out.println("Accepted connection from " + client);
                            client.configureBlocking(false);
                            SelectionKey clientKey = client.register(selector, SelectionKey.OP_WRITE);

                            // By default we start by sending 0
                            ByteBuffer output = ByteBuffer.allocate(4);
                            output.putInt(0);
                            output.flip();

                            clientKey.attach(output);
                        } else if (key.isWritable()) {
                            SocketChannel client = (SocketChannel)key.channel();
                            ByteBuffer output = (ByteBuffer)key.attachment();

                            // If current number is sent, fill buffer with next
                            if (!output.hasRemaining()) {
                                output.rewind();
                                int value = output.getInt();
                                output.clear();
                                output.putInt(value + 1);
                                output.flip();
                            }

                            // Write data anyway, since client is ready for write()
                            client.write(output);
                        }
                    } catch (IOException ex) {
                        key.cancel();
                        try {
                            key.channel().close();
                        } catch (IOException cex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
