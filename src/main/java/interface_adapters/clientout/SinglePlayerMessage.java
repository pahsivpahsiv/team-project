package interface_adapters.clientout;

import network.client.messages.ClientMessage;
import network.client.messages.message_types.MessageType;

/**
 * Wrapper for ClientMessage for single player games.
 * SinglePlayerQueue needs to know message type to call the correct controller.
 * @param type
 * @param data
 */
public record SinglePlayerMessage(MessageType type, ClientMessage data) {
}
