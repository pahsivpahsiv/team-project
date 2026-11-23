package network.client.messages;

import tools.jackson.databind.JsonNode;

public record NetworkRequest(
        int playerId,
        long timestamp,
        MessageType type,
        JsonNode data
) {
    /*
    Placeholder message types
     */
    public enum MessageType{
        SET_FOUND,
        NO_SETS,
        EXIT_GAME,
        CONNECTION_FOUND
    }
}
