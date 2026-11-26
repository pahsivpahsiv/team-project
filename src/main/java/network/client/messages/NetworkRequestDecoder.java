package network.client.messages;

import tools.jackson.databind.ObjectMapper;

/**
 * Mostly exists so we don't have to bundle an ObjectMapper with NetworkRequest objects.
 */
public class NetworkRequestDecoder {

    private final ObjectMapper mapper;
    public NetworkRequestDecoder(){
        this.mapper = new ObjectMapper();
    }

    /**
     * Returns the object representation of NetworkRequest's data payload.
     * @param request A request from the client.
     * @return A specific client message.
     */
    public ClientMessage decode(NetworkRequest request){
        return mapper.treeToValue(request.data(), request.getMessageType());
    }
}
