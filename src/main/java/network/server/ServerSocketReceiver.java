package network.server;

import network.shared.SocketReceiver;

import java.io.IOException;
import java.net.Socket;

public class ServerSocketReceiver extends SocketReceiver {
    public ServerSocketReceiver(Socket socket, GameQueue queue) throws IOException {
        super(socket,queue);
    }
}
