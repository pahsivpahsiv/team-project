package network.client.messages;

import java.util.List;

public record SetFoundMessage(
        List<Integer> cardIds,
        List<Integer> fieldPositions
) {}
