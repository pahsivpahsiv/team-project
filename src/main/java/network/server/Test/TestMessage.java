package network.server.Test;

import network.client.messages.ClientMessage;

public record TestMessage(String body) implements ClientMessage {
}
