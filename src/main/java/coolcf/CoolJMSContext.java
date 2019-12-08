package coolcf;

import com.solacesystems.jcsmp.InvalidPropertiesException;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPFactory;
import com.solacesystems.jcsmp.JCSMPProperties;
import com.solacesystems.jcsmp.JCSMPSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.BytesMessage;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import java.io.Serializable;
import java.util.Hashtable;

public class CoolJMSContext implements JMSContext {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private JCSMPSession sSession;

    protected CoolJMSContext(Hashtable<?,?> env, int sessionMode){
        // Create a JCSMP Session
        JCSMPProperties properties = new JCSMPProperties();

        properties.setProperty(JCSMPProperties.HOST, env.get(Constants.HOST));     // host:port
        properties.setProperty(JCSMPProperties.USERNAME, env.get(Constants.USERNAME)); // client-username
        properties.setProperty(JCSMPProperties.VPN_NAME,  env.get(Constants.VPN_NAME)); // message-vpn
        properties.setProperty(JCSMPProperties.PASSWORD, env.get(Constants.PASSWORD)); // client-password

        try {
            this.sSession =  JCSMPFactory.onlyInstance().createSession(properties);
            this.sSession.connect();
        } catch (InvalidPropertiesException e) {
            e.printStackTrace();
        } catch (JCSMPException e) {
            e.printStackTrace();
        }


    }

    @Override
    public JMSContext createContext(int sessionMode) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer createProducer() {
        return new CoolJMSProducer(sSession);
    }

    @Override
    public String getClientID() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setClientID(String clientID) {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public ConnectionMetaData getMetaData() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public ExceptionListener getExceptionListener() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setExceptionListener(ExceptionListener listener) {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void start() {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void stop() {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void setAutoStart(boolean autoStart) {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public boolean getAutoStart() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void close() {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public BytesMessage createBytesMessage() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public MapMessage createMapMessage() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Message createMessage() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public ObjectMessage createObjectMessage() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable object) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public StreamMessage createStreamMessage() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TextMessage createTextMessage() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TextMessage createTextMessage(String text) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean getTransacted() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public int getSessionMode() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void commit() {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void rollback() {

    }

    @Override
    public void recover() {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public JMSConsumer createConsumer(Destination destination) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSConsumer createConsumer(Destination destination, String messageSelector) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Queue createQueue(String queueName) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Topic createTopic(String topicName) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSConsumer createDurableConsumer(Topic topic, String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSConsumer createSharedDurableConsumer(Topic topic, String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public QueueBrowser createBrowser(Queue queue) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public QueueBrowser createBrowser(Queue queue, String messageSelector) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TemporaryQueue createTemporaryQueue() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public TemporaryTopic createTemporaryTopic() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void unsubscribe(String name) {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void acknowledge() {
        throw new RuntimeException("Not Implemented");

    }
}
