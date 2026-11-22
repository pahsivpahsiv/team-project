package network.client.messages;

import tools.jackson.databind.JsonNode;

public record NetworkRequest(
        int playerId,
        long timestamp,
        MessageType type,
        JsonNode data
) {
    /*
    Placeholder message types for now
     */
    public enum MessageType{
        SET_FOUND,
        NO_SET,
        EXIT_GAME,
        CONNECTION_FOUND
    }
}
