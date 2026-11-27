package interface_adapters.clientout;

import interface_adapters.ControllerInterface;
import network.client.messages.message_types.MessageType;
import network.shared.GameProcessor;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

/**
 * Single-player version of GameQueue.
 */
public class SinglePlayerQueue implements GameProcessor {

    private final BlockingQueue<SinglePlayerMessage> queue;
    private final HashMap<MessageType, ControllerInterface> controllerMap;

    public SinglePlayerQueue(BlockingQueue<SinglePlayerMessage> queue, HashMap<MessageType, ControllerInterface> controllerMap) {
        this.queue = queue;
        this.controllerMap = controllerMap;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SinglePlayerMessage action = queue.take();
                process(action);
            }
        } catch (InterruptedException _){

        }
    }
    private void process(SinglePlayerMessage action){
        controllerMap.get(action.type()).execute(action.data());
    }

    public void incomingMessage(SinglePlayerMessage message) {
        queue.add(message);
    }
}
