package client;

import javax.jms.Message;
import javax.jms.MessageListener;

public class ConsumerListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("Message Received=====================");
    }
}
