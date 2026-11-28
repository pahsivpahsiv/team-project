package network.server.Test;

import interface_adapters.ControllerInterface;
import network.client.messages.message_types.MessageType;
import network.server.GameQueue;
import network.server.ServerConnector;
import network.shared.GameProcessor;

import java.io.IOException;
import java.util.HashMap;

import static network.client.messages.message_types.MessageType.TEST_MESSAGE;

/**
 * For testing only. Has yet to be used.
 */
public class ServerMainTemp {

    public static void main(String[] args) throws IOException {
        var controllerMap = new HashMap<MessageType, ControllerInterface>();
        controllerMap.put(TEST_MESSAGE,new TestController());
        GameQueue game = new GameQueue(controllerMap);
        var connector = new ServerConnector(game);
        Thread t = new Thread(connector);
        t.start();
        game.run();
    }
}
