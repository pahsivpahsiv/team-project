package interface_adapters.clientout;

import network.client.messages.ClientMessage;
import network.client.messages.NetworkRequest;
import network.client.messages.message_types.MessageType;

public class SinglePlayerInput implements NetworkInputBoundary {
    private final SinglePlayerQueue target;
    public SinglePlayerInput(SinglePlayerQueue target){
        this.target = target;
    }
    @Override
    public void sendMessage(MessageType type, ClientMessage message) {
        target.incomingMessage(new SinglePlayerMessage(type, message));
    }
}
