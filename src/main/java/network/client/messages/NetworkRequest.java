package network.client.messages;

import network.client.messages.message_types.MessageType;
import tools.jackson.databind.JsonNode;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * The main data structure being passed between the server and the client.
 * Gets converted to a JSON and back.
 * TODO: Check whether variable name length affects JSON efficiency.
 * @param playerId
 * @param timestamp
 * @param type Type of message (used to reconstruct data into a ClientMessage object).
 * @param data The JsonNode representation of a ClientMessage object.
 */
public record NetworkRequest(
        int playerId,
        long timestamp,
        MessageType type,
        JsonNode data
) implements Delayed {
    /**
     * From the Delayed interface.
     * TODO: Check if this is correct
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return Long.compare(
                this.getDelay(TimeUnit.NANOSECONDS),
                o.getDelay(TimeUnit.NANOSECONDS)
        );
    }
    // TODO: Needs implementation
    @Override
    public long getDelay(TimeUnit unit) {
        // return unit.convert(remaining, TimeUnit.NANOSECONDS);
        return 0;
    }

    /**
     * Returns the correct class type to convert its JSON node into.
     * @return Corresponding message type.
     */
    public Class<? extends ClientMessage> getMessageType(){
        return type.getType();
    }
}
