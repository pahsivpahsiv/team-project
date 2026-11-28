package network.client.outputs;


import network.client.messages.NetworkRequest;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * TODO: Check this works as intended. May need to update.
 * Also, I think I want to mimic the structure of SocketReceiver.
 * Where both the Server and Client extend the same mostly feature-complete class.
 */
public class ClientSocketBroadcaster implements ClientOutput{

    private final Socket socket; // may not need to store
    private final PrintWriter out;
    private final ObjectMapper mapper;

    public ClientSocketBroadcaster(Socket socket) throws IOException {
        this.socket = socket;
        OutputStream outputStream = socket.getOutputStream();
        out = new PrintWriter(outputStream);
        mapper = new ObjectMapper();
    }

    @Override
    public boolean send(NetworkRequest message){
        out.println(mapper.writeValueAsString(message));
        return !out.checkError();
    }
}
