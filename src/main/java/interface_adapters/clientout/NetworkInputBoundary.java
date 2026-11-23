package interface_adapters.clientout;

import network.client.messages.NetworkRequest;

/* Technically, this class should be in network package, but I'm going to leave it here for now
*/
public interface NetworkInputBoundary {
    void sendOutput(NetworkRequest outputData);
}
