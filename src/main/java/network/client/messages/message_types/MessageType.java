package network.client.messages.message_types;

import network.client.messages.ClientMessage;

public enum MessageType{
    SET_FOUND(SetFoundMessage.class),
    NO_SETS(NoSetsMessage.class),
    //EXIT_GAME,
    //CONNECTION_FOUND,
    START_GAME(StartGameMessage.class);

    private final Class<? extends ClientMessage> type;

    /**
     * Constructor for MessageType constants - passes along the class of the message.
     * @param type the type of the input data object needed.
     */
    MessageType(Class<? extends ClientMessage> type) {
        this.type = type;
    }
    public Class<? extends ClientMessage> getType(){
        return type;
    }

}