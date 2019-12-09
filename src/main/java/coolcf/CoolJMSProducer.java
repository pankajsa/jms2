package coolcf;

import com.solacesystems.jcsmp.BytesMessage;
import com.solacesystems.jcsmp.BytesXMLMessage;
import com.solacesystems.jcsmp.DeliveryMode;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPFactory;
import com.solacesystems.jcsmp.JCSMPSession;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jcsmp.XMLMessageProducer;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.CompletionListener;
import javax.jms.Destination;
import javax.jms.JMSProducer;
import javax.jms.Message;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class CoolJMSProducer implements JMSProducer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private JCSMPSession sSession;

    protected CoolJMSProducer(JCSMPSession sSession) {
        this.sSession = sSession;
    }

    private void sendSolace(Destination sDest, BytesXMLMessage msg){
        // XMLMessage -> BytesXMLMessage -> Message -> BytesMessage, TextMessage, StreamMessage, MapMessage, XMLContentMessage
        // JMS: TextMessage, BytesMessage, ObjectMessage, StreamMessage, MapMessage
        try {
            logger.debug("sendNEW=============");
            XMLMessageProducer prod = sSession.getMessageProducer(new SolaceStreamingPublishAdapter());

            msg.setDeliveryMode(DeliveryMode.PERSISTENT);
//            MsgInfo msgCorrelationInfo = new MsgInfo(1);
//            msgCorrelationInfo.sessionIndependentMessage = msg;
//            msg.setCorrelationKey(msgCorrelationInfo);

            if (((CoolDestination)sDest).isQueue()){
                com.solacesystems.jcsmp.Queue queue = JCSMPFactory.onlyInstance().createQueue(((CoolDestination)sDest).getSDestination().getName());
                prod.send(msg, queue);
            }
            else{
                com.solacesystems.jcsmp.Topic topic = JCSMPFactory.onlyInstance().createTopic(((CoolDestination)sDest).getSDestination().getName());
                prod.send(msg, topic);

            }


//            prod.send( ((CoolTextMessage)message).getSTextMessage(), this.sDest );
        } catch (JCSMPException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public JMSProducer send(Destination destination, Message message) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer send(Destination destination, String body) {
        TextMessage msg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
        msg.setText(body);
        sendSolace(destination, msg);
        return this;
    }

    @Override
    public JMSProducer send(Destination destination, Map<String, Object> body) {
        return this.send(destination, (Serializable)body);
//        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer send(Destination destination, byte[] body) {
        BytesMessage msg = JCSMPFactory.onlyInstance().createMessage(BytesMessage.class);
        msg.setData(body);
        sendSolace(destination, msg);
        return this;
    }

    @Override
    public JMSProducer send(Destination destination, Serializable body) {

        byte[] data = SerializationUtils.serialize(body);
        BytesMessage msg = JCSMPFactory.onlyInstance().createMessage(BytesMessage.class);
        msg.setData(data);
        sendSolace(destination, msg);
        return this;
    }

    @Override
    public JMSProducer setDisableMessageID(boolean value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean getDisableMessageID() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setDisableMessageTimestamp(boolean value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean getDisableMessageTimestamp() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setDeliveryMode(int deliveryMode) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public int getDeliveryMode() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setPriority(int priority) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public int getPriority() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setTimeToLive(long timeToLive) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public long getTimeToLive() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setDeliveryDelay(long deliveryDelay) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public long getDeliveryDelay() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setAsync(CompletionListener completionListener) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public CompletionListener getAsync() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, boolean value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, byte value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, short value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, int value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, long value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, float value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, double value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, String value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setProperty(String name, Object value) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer clearProperties() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean propertyExists(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean getBooleanProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public byte getByteProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public short getShortProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public int getIntProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public long getLongProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public float getFloatProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public double getDoubleProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public String getStringProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Object getObjectProperty(String name) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Set<String> getPropertyNames() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setJMSCorrelationIDAsBytes(byte[] correlationID) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public byte[] getJMSCorrelationIDAsBytes() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setJMSCorrelationID(String correlationID) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public String getJMSCorrelationID() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setJMSType(String type) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public String getJMSType() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSProducer setJMSReplyTo(Destination replyTo) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Destination getJMSReplyTo() {
        throw new RuntimeException("Not Implemented");
    }
}
