package network.server.Test;

import interface_adapters.clientout.NetworkInputBoundary;
import network.client.messages.ClientMessage;
import network.client.messages.message_types.MessageType;
import network.client.outputs.ClientOutput;
import network.client.outputs.ClientRequestComposer;
import network.client.outputs.ClientSocketBroadcaster;

import java.io.IOException;
import java.net.Socket;

/**
 * For testing only. Has yet to be used.
 */
public class ClientMainTemp {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClientMessage test = new TestMessage("Hello 101");
        Socket socket = new Socket("localhost",5000);
        ClientOutput broadcaster = new ClientSocketBroadcaster(socket);
        NetworkInputBoundary out = new ClientRequestComposer(broadcaster,5);
        out.sendMessage(MessageType.TEST_MESSAGE,test);
        while (true){
            System.out.println("hi");
        }
    }
}
