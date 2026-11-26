package interface_adapters.clientout;

import network.client.messages.NetworkRequest;

/**
 * Technically, this class should be in the network package, but I'm going to leave it here for now.
 * The client-side controller relies on this interface to communicate with the network.
 */
public interface NetworkInputBoundary {
    void sendMessage(NetworkRequest message);
}
