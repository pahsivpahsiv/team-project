package network.server;

import network.shared.SocketReceiver;

import java.io.IOException;
import java.net.Socket;

/**
 * Implements SocketReceiver.
 * A *Runnable* that receives messages from client and passes them to GameQueue.
 */
public class ServerSocketReceiver extends SocketReceiver {
    public ServerSocketReceiver(Socket socket, GameQueue queue) throws IOException {
        super(socket,queue);
    }
}
