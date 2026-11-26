package network.client.inputs;

import network.shared.SocketReceiver;

import java.io.IOException;
import java.net.Socket;

/**
 * Client-side network receiver.
 */
public class ClientSocketReceiver extends SocketReceiver {
    public ClientSocketReceiver(Socket socket, ClientEntryPoint destination) throws IOException {
        super(socket,destination);
    }
}
