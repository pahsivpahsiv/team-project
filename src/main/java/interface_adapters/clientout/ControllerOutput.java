package interface_adapters.clientout;

import network.client.outputs.NetworkOutputData;

public interface ControllerOutput {
    void sendOutput(NetworkOutputData outputData);
}
