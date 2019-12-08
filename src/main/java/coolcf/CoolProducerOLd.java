package coolcf;

import com.solacesystems.jcsmp.BytesXMLMessage;
import com.solacesystems.jcsmp.DeliveryMode;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPFactory;
import com.solacesystems.jcsmp.JCSMPSession;
import com.solacesystems.jcsmp.JCSMPStreamingPublishCorrelatingEventHandler;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jcsmp.XMLMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.CompletionListener;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

public class CoolProducerOLd implements MessageProducer, JCSMPStreamingPublishCorrelatingEventHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JCSMPSession sSession;
    private CoolDestination sDest;
    private boolean isQueue;
    private CompletionListener completionListener;

    @Override
    public void responseReceivedEx(Object object) {
        System.out.println("responseReceivedEx");

        if (object instanceof MsgInfo) {
            MsgInfo i = (MsgInfo) object;
            i.acked = true;
            i.publishedSuccessfully = true;
            System.out.printf("response (accepted) received for %s \n", i);
        }
        this.completionListener.onCompletion(null);

    }

    @Override
    public void handleErrorEx(Object object, JCSMPException cause, long timestamp) {
        System.out.println("handleErrorEx");

        if (object instanceof MsgInfo) {
            MsgInfo i = (MsgInfo) object;
            i.acked = true;
            System.out.printf("received for %s, error was %s \n", i, cause);
        }
        this.completionListener.onException(null,null);

    }

    @Override
    public void handleError(String messageID, JCSMPException e, long timestamp) {
        // Never called

        System.out.printf("Producer received error for msg: %s@%s - %s%n",
                messageID,timestamp,e);

    }

    @Override
    public void responseReceived(String messageID) {
        // Never called

        System.out.println("Producer received response for msg: " + messageID);

    }


    class MsgInfo {
        public volatile boolean acked = false;
        public volatile boolean publishedSuccessfully = false;
        public BytesXMLMessage sessionIndependentMessage = null;
        public final long id;

        public MsgInfo(long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return String.format("Message ID: %d, PubConf: %b, PubSuccessful: %b", id, acked, publishedSuccessfully);
        }
    }




    public CoolProducerOLd(JCSMPSession sSession, Destination destination) {
        this.sSession = sSession;
//        this.sDest = ((CoolDestination)destination).getSDestination();
        this.sDest = (CoolDestination)destination;

    }


    @Override
    public void setDisableMessageID(boolean b) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public boolean getDisableMessageID() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setDisableMessageTimestamp(boolean b) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public boolean getDisableMessageTimestamp() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setDeliveryMode(int i) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public int getDeliveryMode() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setPriority(int i) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public int getPriority() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setTimeToLive(long l) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public long getTimeToLive() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setDeliveryDelay(long deliveryDelay) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public long getDeliveryDelay() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Destination getDestination() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void close() throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void send(Message message) throws JMSException {
        try {
            logger.debug("send");


            XMLMessageProducer prod = sSession.getMessageProducer(this);
            logger.debug("Queue is " +  sDest.getSDestination().getName());
//            com.solacesystems.jcsmp.Queue queue = JCSMPFactory.onlyInstance().createQueue("Q/tutorial");
            com.solacesystems.jcsmp.Queue queue = JCSMPFactory.onlyInstance().createQueue(sDest.getSDestination().getName());

            TextMessage msg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);


            msg.setDeliveryMode(DeliveryMode.PERSISTENT);
            String text = "Persistent Queue Tutorial! " +
                    new java.util.Date();
            msg.setText(text);
            prod.send(msg, queue);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


//            prod.send( ((CoolTextMessage)message).getSTextMessage(), this.sDest );
        } catch (JCSMPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }


    @Override
    public void send(Destination destination, Message message) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void send(Destination destination, Message message, int i, int i1, long l) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void send(Message message, CompletionListener completionListener) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {
        try {
            logger.debug("sendNEW=============");
            this.completionListener = completionListener;
            XMLMessageProducer prod = sSession.getMessageProducer(this);
//            XMLMessageProducer prod = sSession.getMessageProducer(new PubCallback());

            logger.debug("Queue is " +  sDest.getSDestination().getName());

            TextMessage msg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);

            msg.setDeliveryMode(DeliveryMode.PERSISTENT);
            MsgInfo msgCorrelationInfo = new MsgInfo(1);
            msgCorrelationInfo.sessionIndependentMessage = msg;
            msg.setCorrelationKey(msgCorrelationInfo);

            String text = "Persistent Queue Tutorial! " +
                    new java.util.Date();
            msg.setText(text);


            if (((CoolDestination)sDest).isQueue()){
                com.solacesystems.jcsmp.Queue queue = JCSMPFactory.onlyInstance().createQueue(sDest.getSDestination().getName());
                prod.send(msg, queue);
            }
            else{
                com.solacesystems.jcsmp.Topic topic = JCSMPFactory.onlyInstance().createTopic(sDest.getSDestination().getName());
                prod.send(msg, topic);

            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            prod.send( ((CoolTextMessage)message).getSTextMessage(), this.sDest );
        } catch (JCSMPException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void send(Destination destination, Message message, CompletionListener completionListener) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

}