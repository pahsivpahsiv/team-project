package interface_adapters.clientout;

import network.client.messages.NetworkRequest;

/**
 * Creates ClientMessage objects wrapped in LocalOutputData
 * The Controller can simply feed it the relevant data
 */
public class MessageBuilder {
    private final int playerId;
    private network.client.messages.NetworkRequest output;
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
