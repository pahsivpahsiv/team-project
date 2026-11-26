package interface_adapters;

import network.client.messages.ClientMessage;

/**
 * Interface for all server-side controllers. Used by network layer to store the controllers.
 */
public interface ControllerInterface {
    void execute(ClientMessage input);
}
