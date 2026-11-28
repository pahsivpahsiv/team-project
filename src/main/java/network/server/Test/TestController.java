package network.server.Test;

import interface_adapters.ControllerInterface;
import network.client.messages.ClientMessage;

public class TestController implements ControllerInterface {
    @Override
    public void execute(ClientMessage input) {
        System.out.println("(TestController) Successfully Executed. Message:" + ((TestMessage)input).body());
    }
}
