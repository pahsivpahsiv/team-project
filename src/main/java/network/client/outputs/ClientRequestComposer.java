package network.client.outputs;

import interface_adapters.clientout.NetworkInputBoundary;
import network.client.messages.ClientMessage;
import network.client.messages.NetworkRequest;
import network.client.messages.message_types.MessageType;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

/**
 * TODO: Check this works as intended. May need to update.
 */
public class ClientRequestComposer implements NetworkInputBoundary {

    private final ClientOutput broadcaster;
    private final int playerId;
    private final ObjectMapper mapper;
    public ClientRequestComposer(ClientOutput broadcaster, int playerId) {
        this.broadcaster = broadcaster;
        this.playerId = playerId;
        this.mapper = new ObjectMapper();
    }
    @Override
    public void sendMessage(MessageType type, ClientMessage message){
        JsonNode data = mapper.valueToTree(message);
        long time = System.nanoTime();
        NetworkRequest request = new NetworkRequest(playerId,time,type,data);
        broadcaster.send(request);
    }
}
