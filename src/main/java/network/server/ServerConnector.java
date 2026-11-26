package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the class responsible for opening a listening port, and accepting each incoming player.
 * Unless there is a player limit, it should just keep accepting players.
 * ServerSocket.accept() waits on a port until someone connects.
 * I'm not too sure how it works.
 * TODO: Implement this class.
 * Currently has a temporary scaffold for taking input, but not outputs.
 */
public class ServerConnector implements Runnable {
    private final ServerSocket listener;
    private final List<Thread> connections;
    private final GameQueue queue;
    public ServerConnector(GameQueue queue){
        this.queue = queue;
        this.connections = new ArrayList<Thread>();
        try {
            this.listener = new ServerSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run(){
        while (true){
            try {
                Socket socket = listener.accept(); // blocking operation
                /* ServerSocketReceiver is a runnable that listens for client inputs.
                 * Consider using an ExecutorService rather than manually creating threads.
                 */
                Thread t = new Thread(new ServerSocketReceiver(socket,queue));
                connections.add(t);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
