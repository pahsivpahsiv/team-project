package network.client.messages.message_types;

import network.client.messages.ClientMessage;

public record StartGameMessage(
        int gameMode,
        int hostId
) implements ClientMessage {}
