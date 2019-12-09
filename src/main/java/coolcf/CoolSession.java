package coolcf;

import com.solacesystems.jcsmp.InvalidPropertiesException;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPFactory;
import com.solacesystems.jcsmp.JCSMPProperties;
import com.solacesystems.jcsmp.JCSMPSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import java.io.Serializable;
import java.util.Hashtable;

public class CoolSession implements Session{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private JCSMPSession sSession;

    public CoolSession(Hashtable<?,?> env, boolean isTransacted, int ackMode) {
        // Create a JCSMP Session
        JCSMPProperties properties = new JCSMPProperties();

        properties.setProperty(JCSMPProperties.HOST, env.get(Constants.HOST));     // host:port
        properties.setProperty(JCSMPProperties.USERNAME, env.get(Constants.USERNAME)); // client-username
        properties.setProperty(JCSMPProperties.VPN_NAME,  env.get(Constants.VPN_NAME)); // message-vpn
        properties.setProperty(JCSMPProperties.PASSWORD, env.get(Constants.PASSWORD)); // client-password
        properties.setProperty(JCSMPProperties.PUB_ACK_WINDOW_SIZE, env.get(Constants.PUB_ACK_WINDOW_SIZE)); // client-password
        logger.debug("Creating Session: " + env.toString());
        try {
            this.sSession =  JCSMPFactory.onlyInstance().createSession(properties);
            this.sSession.connect();
        } catch (InvalidPropertiesException e) {
            throw new RuntimeException(e);

        } catch (JCSMPException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MapMessage createMapMessage() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Message createMessage() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable serializable) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TextMessage createTextMessage() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
//        throw new RuntimeException("Not Implemented");
        return new CoolTextMessage(text);

    }

    @Override
    public boolean getTransacted() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public int getAcknowledgeMode() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void commit() throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void rollback() throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void close() throws JMSException {
        sSession.closeSession();

    }

    @Override
    public void recover() throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setMessageListener(MessageListener messageListener) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void run() {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public CoolProducer createProducer(Destination destination) throws JMSException {
        return new CoolProducer(sSession, destination);

    }

    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String s) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String selector, boolean onLocal) throws JMSException {
        return new CoolConsumer(sSession, destination, selector, onLocal );
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Queue createQueue(String s) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Topic createTopic(String s) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String s) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String s, String s1, boolean b) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public QueueBrowser createBrowser(Queue queue, String s) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void unsubscribe(String s) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }
}