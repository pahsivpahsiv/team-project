package network.client.outputs;


import network.client.messages.NetworkRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketBroadcaster implements ClientOutput{

    private Socket socket; // may not need to store
    private PrintWriter out;
    public ClientSocketBroadcaster(Socket socket) throws IOException {
        this.socket = socket;
        OutputStream outputStream = socket.getOutputStream();
        out = new PrintWriter(outputStream);
    }

    @Override
    public int send(NetworkRequest message){
        out.println();
        return 0; // fix to return
    }
}
