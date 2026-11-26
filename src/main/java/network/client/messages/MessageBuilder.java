package network.client.messages;

/**
 * Builds NetworkRequest objects.
 * The Controller (or Presenter) can simply feed it the relevant data.
 * The hope is that this removes the need for the controller/presenter to know about
 * the specific implementations of ClientMessage/ServerMessage/whatever I call it.
 * As such, we might want a separate version of this class for the client vs for the server.
 * TODO: Implement this class.
 */
public class MessageBuilder {
    private final int playerId;
    private NetworkRequest output;
    public MessageBuilder(int playerId){
        this.playerId = playerId;
    }
    public MessageBuilder cardIds(){
        return this;
    }
    public NetworkRequest build(){
        return output;
    }

}
