package network.client.outputs;

import network.client.messages.NetworkRequest;

public interface ClientOutput {

    /**
     * Sends the JSON representation of NetworkRequest to the server.
     * @param message the message to send to server.
     * @return true if the message was sent successfully, false otherwise
     */
    boolean send(NetworkRequest message);

}
