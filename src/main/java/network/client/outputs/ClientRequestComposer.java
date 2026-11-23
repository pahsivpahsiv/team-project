package network.client.outputs;

import interface_adapters.clientout.NetworkInputBoundary;
import network.client.messages.NetworkRequest;

public class ClientRequestComposer implements NetworkInputBoundary {

    private final ClientOutput broadcaster;
    public ClientRequestComposer(ClientSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }
    @Override
    public void sendOutput(NetworkRequest outputData){
        broadcaster.send(outputData);
    }
}
