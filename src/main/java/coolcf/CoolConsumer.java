package coolcf;

import com.solacesystems.jcsmp.BytesXMLMessage;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPFactory;
import com.solacesystems.jcsmp.JCSMPSession;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jcsmp.Topic;
import com.solacesystems.jcsmp.XMLMessageConsumer;
import com.solacesystems.jcsmp.XMLMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

public class CoolConsumer implements MessageConsumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String selector;
    private boolean onLocal;
    private Destination dest;
    private JCSMPSession sSession;

    public CoolConsumer(JCSMPSession sSession, Destination destination, String selector, boolean onLocal) {
        this.selector = selector;
        this.onLocal = onLocal;
        this.sSession = sSession;

    }

    @Override
    public String getMessageSelector() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        logger.debug("setMessageListener");

        try {
            final XMLMessageConsumer cons = sSession.getMessageConsumer(new XMLMessageListener() {

                @Override
                public void onReceive(BytesXMLMessage msg) {
                    if (msg instanceof TextMessage) {
                        System.out.printf("TextMessage received: '%s'%n",
                                ((TextMessage)msg).getText());
                    } else {
                        System.out.println("Message received.");
                    }
                    System.out.printf("Message Dump:%n%s%n",msg.dump());
                    listener.onMessage(null);
                }

                @Override
                public void onException(JCSMPException e) {
                    System.out.printf("Consumer received exception: %s%n",e);
                }
            });
            Topic topic = JCSMPFactory.onlyInstance().createTopic("tutorial/topic");
            sSession.addSubscription(topic);
            cons.start();

        } catch (JCSMPException e) {
            throw new RuntimeException(e);

        }





    }

    @Override
    public Message receive() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Message receive(long timeout) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void close() throws JMSException {
        throw new RuntimeException("Not Implemented");

    }
}
