package network.client.messages;

import java.util.List;

public record StartGameMessage(
        int gameMode,
        int hostId
) implements ClientMessage {}
