package coolcf;

import com.solacesystems.jcsmp.JCSMPFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Enumeration;

public class CoolTextMessage implements TextMessage {
    private String text;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    com.solacesystems.jcsmp.TextMessage sMsg;

    public com.solacesystems.jcsmp.TextMessage getSTextMessage(){
        return sMsg;
    }

    public CoolTextMessage(String text) {
        this.text = text;
        sMsg = JCSMPFactory.onlyInstance().createMessage(com.solacesystems.jcsmp.TextMessage.class);
    }

    @Override
    public void setText(String s) throws JMSException {

    }

    @Override
    public String getText() throws JMSException {
        return null;
    }

    @Override
    public String getJMSMessageID() throws JMSException {
        return null;
    }

    @Override
    public void setJMSMessageID(String s) throws JMSException {

    }

    @Override
    public long getJMSTimestamp() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSTimestamp(long l) throws JMSException {

    }

    @Override
    public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
        return new byte[0];
    }

    @Override
    public void setJMSCorrelationIDAsBytes(byte[] bytes) throws JMSException {

    }

    @Override
    public void setJMSCorrelationID(String s) throws JMSException {

    }

    @Override
    public String getJMSCorrelationID() throws JMSException {
        return null;
    }

    @Override
    public Destination getJMSReplyTo() throws JMSException {
        return null;
    }

    @Override
    public void setJMSReplyTo(Destination destination) throws JMSException {

    }

    @Override
    public Destination getJMSDestination() throws JMSException {
        return null;
    }

    @Override
    public void setJMSDestination(Destination destination) throws JMSException {

    }

    @Override
    public int getJMSDeliveryMode() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSDeliveryMode(int i) throws JMSException {

    }

    @Override
    public boolean getJMSRedelivered() throws JMSException {
        return false;
    }

    @Override
    public void setJMSRedelivered(boolean b) throws JMSException {

    }

    @Override
    public String getJMSType() throws JMSException {
        return null;
    }

    @Override
    public void setJMSType(String s) throws JMSException {

    }

    @Override
    public long getJMSExpiration() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSExpiration(long l) throws JMSException {

    }

    /**
     * Gets the message's delivery time value.
     *
     * <p>
     * When a message is sent, the {@code JMSDeliveryTime} header field is
     * left unassigned. After completion of the {@code send} or
     * {@code publish} method, it holds the delivery time of the message.
     * This is the the difference, measured in milliseconds,
     * between the delivery time and midnight, January 1, 1970 UTC.
     * <p>
     * A message's delivery time is the earliest time when a JMS provider may
     * deliver the message to a consumer. The provider must not deliver messages
     * before the delivery time has been reached.
     *
     * @return the message's delivery time value
     * @throws JMSException if the JMS provider fails to get the delivery time due to
     *                      some internal error.
     * @see Message#setJMSDeliveryTime(long)
     * @since JMS 2.0
     */
    @Override
    public long getJMSDeliveryTime() throws JMSException {
        return 0;
    }

    /**
     * Sets the message's delivery time value.
     * <p>
     * This method is for use by JMS providers only to set this field when a
     * message is sent. This message cannot be used by clients to configure the
     * delivery time of the message. This method is public to allow a JMS
     * provider to set this field when sending a message whose implementation is
     * not its own.
     *
     * @param deliveryTime the message's delivery time value
     * @throws JMSException if the JMS provider fails to set the delivery time due to
     *                      some internal error.
     * @see Message#getJMSDeliveryTime()
     * @since JMS 2.0
     */
    @Override
    public void setJMSDeliveryTime(long deliveryTime) throws JMSException {

    }

    @Override
    public int getJMSPriority() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSPriority(int i) throws JMSException {

    }

    @Override
    public void clearProperties() throws JMSException {

    }

    @Override
    public boolean propertyExists(String s) throws JMSException {
        return false;
    }

    @Override
    public boolean getBooleanProperty(String s) throws JMSException {
        return false;
    }

    @Override
    public byte getByteProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public short getShortProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public int getIntProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public long getLongProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public float getFloatProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public double getDoubleProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public String getStringProperty(String s) throws JMSException {
        return null;
    }

    @Override
    public Object getObjectProperty(String s) throws JMSException {
        return null;
    }

    @Override
    public Enumeration getPropertyNames() throws JMSException {
        return null;
    }

    @Override
    public void setBooleanProperty(String s, boolean b) throws JMSException {

    }

    @Override
    public void setByteProperty(String s, byte b) throws JMSException {

    }

    @Override
    public void setShortProperty(String s, short i) throws JMSException {

    }

    @Override
    public void setIntProperty(String s, int i) throws JMSException {

    }

    @Override
    public void setLongProperty(String s, long l) throws JMSException {

    }

    @Override
    public void setFloatProperty(String s, float v) throws JMSException {

    }

    @Override
    public void setDoubleProperty(String s, double v) throws JMSException {

    }

    @Override
    public void setStringProperty(String s, String s1) throws JMSException {

    }

    @Override
    public void setObjectProperty(String s, Object o) throws JMSException {

    }

    @Override
    public void acknowledge() throws JMSException {

    }

    @Override
    public void clearBody() throws JMSException {

    }

    /**
     * Returns the message body as an object of the specified type.
     * This method may be called on any type of message except for
     * <tt>StreamMessage</tt>. The message
     * body must be capable of being assigned to the specified type. This means
     * that the specified class or interface must be either the same as, or a
     * superclass or superinterface of, the class of the message body.
     * If the message has no body then any type may be specified and null is returned.
     * <p>
     *
     * @param c The type to which the message body will be assigned. <br>
     *          If the message is a {@code TextMessage} then this parameter must
     *          be set to {@code String.class} or another type to which
     *          a {@code String} is assignable. <br>
     *          If the message is a {@code ObjectMessage} then parameter must
     *          must be set to {@code java.io.Serializable.class} or
     *          another type to which the body is assignable. <br>
     *          If the message is a {@code MapMessage} then this parameter must
     *          be set to {@code java.util.Map.class} (or {@code java.lang.Object.class}). <br>
     *          If the message is a {@code BytesMessage} then this parameter must
     *          be set to {@code byte[].class} (or {@code java.lang.Object.class}). This method
     *          will reset the {@code BytesMessage} before and after use.<br>
     *          If the message is a
     *          {@code TextMessage}, {@code ObjectMessage}, {@code MapMessage}
     *          or {@code BytesMessage} and the message has no body,
     *          then the above does not apply and this parameter may be set to any type;
     *          the returned value will always be null.<br>
     *          If the message is a {@code Message} (but not one of its subtypes)
     *          then this parameter may be set to any type;
     *          the returned value will always be null.
     * @return the message body
     * @throws MessageFormatException <ul>
     *                                               <li>if the message is a {@code StreamMessage}
     *                                               <li> if the message body cannot be assigned to
     *                                               the specified type
     *                                               <li> if the message is an {@code ObjectMessage} and object
     *                                               deserialization fails.
     *                                               </ul>
     * @throws JMSException           if the JMS provider fails to get the message body due to
     *                                some internal error.
     * @since JMS 2.0
     */
    @Override
    public <T> T getBody(Class<T> c) throws JMSException {
        return null;
    }

    /**
     * Returns whether the message body is capable of being assigned to the
     * specified type. If this method returns true then a subsequent call to the
     * method {@code getBody} on the same message with the same type argument would not throw a
     * MessageFormatException.
     * <p>
     * If the message is a {@code StreamMessage} then false is always returned.
     * If the message is a {@code ObjectMessage} and object deserialization
     * fails then false is returned. If the message has no body then any type may be specified and true is
     * returned.
     *
     * @param c The specified type <br>
     *          If the message is a {@code TextMessage} then this method will
     *          only return true if this parameter is set to
     *          {@code String.class} or another type to which a {@code String}
     *          is assignable. <br>
     *          If the message is a {@code ObjectMessage} then this
     *          method will only return true if this parameter is set to
     *          {@code java.io.Serializable.class} or another class to
     *          which the body is assignable. <br>
     *          If the message is a {@code MapMessage} then this method
     *          will only return true if this parameter is set to
     *          {@code java.util.Map.class} (or {@code java.lang.Object.class}). <br>
     *          If the message is a {@code BytesMessage} then this this
     *          method will only return true if this parameter is set to
     *          {@code byte[].class} (or {@code java.lang.Object.class}). <br>
     *          If the message is a
     *          {@code TextMessage}, {@code ObjectMessage}, {@code MapMessage}
     *          or {@code BytesMessage} and the message has no body,
     *          then the above does not apply and this method will return true
     *          irrespective of the value of this parameter.<br>
     *          If the message is a
     *          {@code Message} (but not one of its subtypes)
     *          then this method will return true
     *          irrespective of the value of this parameter.
     * @return whether the message body is capable of being assigned to the
     * specified type
     * @throws JMSException if the JMS provider fails to return a value due to some
     *                      internal error.
     */
    @Override
    public boolean isBodyAssignableTo(Class c) throws JMSException {
        return false;
    }
}
