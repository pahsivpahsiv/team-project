package network.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Forwards incoming requests to MessageDestination (e.g. GameQueue).
 * This class may be sufficient on its own,
 * given the client and server implementation are currently identical.
 */
public abstract class SocketReceiver implements Runnable {
    private final BufferedReader in;
    private final Socket socket;
    private final MessageDestination endPoint;
    public SocketReceiver(Socket socket, MessageDestination endPoint) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        this.endPoint = endPoint;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                // readLine() returns null when connection terminates, otherwise blocks until input
                endPoint.incomingMessage(message);
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
