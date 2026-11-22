package network.client.outputs;

import interface_adapters.clientout.ControllerOutput;

public class ClientRequestComposer implements ControllerOutput {

    private final ClientSocketBroadcaster broadcaster;
    public ClientRequestComposer(ClientSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }
    @Override
    public void sendOutput(NetworkOutputData outputData){

    }
}
