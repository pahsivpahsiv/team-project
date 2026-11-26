package network.shared;

/**
 * Destination for network messages (in JSON form) from SocketReceiver.
 * Generally decodes them and then acts on them.
 */
public interface MessageDestination {
    void incomingMessage(String message);
}
