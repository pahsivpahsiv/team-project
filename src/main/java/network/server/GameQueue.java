package network.server;

import network.client.messages.NetworkRequest;

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
    private DelayQueue<NetworkRequest> actionQueue;

    @Override
    public void run() {
        try {
            NetworkRequest action = actionQueue.take();
            process(action);
        } catch (InterruptedException _){

        }
    }
    private void process(NetworkRequest action){
        switch (action.type()){

        }
    }
}
