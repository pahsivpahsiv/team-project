package network.client.outputs;

import network.client.messages.NetworkRequest;

public interface ClientOutput {

    int send(NetworkRequest message);

}
