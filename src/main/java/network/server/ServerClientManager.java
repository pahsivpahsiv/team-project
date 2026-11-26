package network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerClientManager implements Runnable {

    private final BufferedReader in;
    private final Socket socket;
    private final GameQueue queue;
    public ServerClientManager(Socket socket, GameQueue queue) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                // readLine() returns null when connection terminates, otherwise blocks until input
                queue.incomingMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
