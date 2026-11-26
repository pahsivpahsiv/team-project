package network.server;

import interface_adapters.ControllerInterface;
import network.client.messages.NetworkRequest;
import network.client.messages.NetworkRequestDecoder;
import network.client.messages.message_types.MessageType;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.concurrent.DelayQueue;

/**
 * Here's where the main actions are done, and a lot of thread stuff gets initialized
 * I think we should use a DelayedQueue. This basically handles priority based on expiry time.
 * We can just set expiry time to e.g. 200 ms after client timestamp
 * Should think about what we want to actually put in the queue.
 * Is NetworkRequest really the best option, or do we want to rewrap it?
 *
 * Maybe we can toss StartGame use case into the queue when we start a game
 */
public class GameQueue implements Runnable {
    private DelayQueue<NetworkRequest> queue;
    private final ObjectMapper mapper;
    private HashMap<MessageType, ControllerInterface> controllerMap;
    private final NetworkRequestDecoder decoder;

    public GameQueue(HashMap<MessageType, ControllerInterface> controllerMap) {
        this.controllerMap = controllerMap;
        this.mapper = new ObjectMapper();
        this.decoder = new NetworkRequestDecoder();
        this.queue = new DelayQueue<NetworkRequest>();
    }

    @Override
    public void run() {
        try {
            NetworkRequest action = queue.take();
            process(action);
        } catch (InterruptedException _){

        }
    }

    /**
     * Gets the appropriate controller from map, then passes the stored input data into its execute().
     * @param request NetworkRequest object from queue.
     */
    private void process(NetworkRequest request){
        controllerMap.get(request.type()).execute(decoder.decode(request));
    }

    /**
     * Adds a NetworkRequest to queue.
     * TODO: Add delay to requests.
     * @param message JSON representation of the NetworkRequest object.
     */
    public void incomingMessage(String message) {
        var request = mapper.readValue(message, NetworkRequest.class);
        queue.offer(request);
    }
}
