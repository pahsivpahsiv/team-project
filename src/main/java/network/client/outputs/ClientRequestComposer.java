package network.client.outputs;

import interface_adapters.clientout.NetworkInputBoundary;
import network.client.messages.NetworkRequest;

/**
 * TODO: Check this works as intended. May need to update.
 */
public class ClientRequestComposer implements NetworkInputBoundary {

    private final ClientOutput broadcaster;
    public ClientRequestComposer(ClientSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }
    @Override
    public void sendMessage(NetworkRequest message){
        broadcaster.send(message);
    }
}
