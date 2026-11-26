package network.client.messages.message_types;

import network.client.messages.ClientMessage;

import java.util.List;

public record SetFoundMessage (
        List<Integer> cardIds,
        List<Integer> fieldPositions
) implements ClientMessage {}
