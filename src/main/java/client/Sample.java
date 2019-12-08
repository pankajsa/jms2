package client;

import com.solacesystems.jcsmp.*;

import javax.jms.JMSException;
import java.text.DateFormat;
import java.util.Date;

public class Sample {
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

    class PubCallback implements JCSMPStreamingPublishCorrelatingEventHandler {

        @Override
        public void handleErrorEx(Object key, JCSMPException cause, long timestamp) {
            System.out.println("handleErrorEx");

            if (key instanceof MsgInfo) {
                MsgInfo i = (MsgInfo) key;
                i.acked = true;
                System.out.printf("received for %s, error was %s \n", i, cause);
            }
        }

        @Override
        public void responseReceivedEx(Object key) {
            System.out.println("responseReceivedEx");

            if (key instanceof MsgInfo) {
                MsgInfo i = (MsgInfo) key;
                i.acked = true;
                i.publishedSuccessfully = true;
                System.out.printf("response (accepted) received for %s \n", i);
            }
        }

        @Override
        public void handleError(String messageID, JCSMPException cause, long timestamp) {
            System.out.println("handleError");

            // Never called
        }

        @Override
        public void responseReceived(String messageID) {
            System.out.println("responseReceived");

            // Never called
        }
    }

    public void testPQL() throws JCSMPException, JMSException, InterruptedException {
        // Check command line arguments

        System.out.println("QueueProducer initializing...");
        // Create a JCSMP Session
        final JCSMPProperties properties = new JCSMPProperties();
        properties.setProperty(JCSMPProperties.HOST, "localhost");     // host:port
        properties.setProperty(JCSMPProperties.USERNAME, "default"); // client-username
        properties.setProperty(JCSMPProperties.VPN_NAME,  "default"); // message-vpn
        properties.setProperty(JCSMPProperties.PASSWORD, "default"); // client-password
        JCSMPSession session = JCSMPFactory.onlyInstance().createSession(properties);

        session.connect();

        String queueName = "Q/tutorial";
        System.out.printf("Attempting to provision the queue '%s' on the appliance.%n", queueName);
        EndpointProperties endpointProps = new EndpointProperties();
        endpointProps.setPermission(EndpointProperties.PERMISSION_CONSUME);
        endpointProps.setAccessType(EndpointProperties.ACCESSTYPE_EXCLUSIVE);
        // create the queue object locally
        Queue queue = JCSMPFactory.onlyInstance().createQueue(queueName);
        // Actually provision it, and do not fail if it already exists
//        session.provision(queue, endpointProps
//                ,JCSMPSession.FLAG_IGNORE_ALREADY_EXISTS
//        );

        /** Anonymous inner-class for handling publishing events */

        final XMLMessageProducer prod = session.getMessageProducer(new PubCallback());


//        final XMLMessageProducer prod = session.getMessageProducer(
//                new JCSMPStreamingPublishEventHandler() {
//                    @Override
//                    public void responseReceived(String messageID) {
//                        System.out.printf("Producer received response for msg ID #%s%n",messageID);
//                    }
//                    @Override
//                    public void handleError(String messageID, JCSMPException e, long timestamp) {
//                        System.out.printf("Producer received error for msg ID %s @ %s - %s%n",
//                                messageID,timestamp,e);
//                    }
//                });

        // Publish-only session is now hooked up and running!
        System.out.printf("Connected. About to send message to queue '%s'...%n",queue.getName());

        TextMessage msg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
        msg.setDeliveryMode(DeliveryMode.PERSISTENT);
        String text = "Persistent Queue Tutorial! "+ DateFormat.getDateTimeInstance().format(new Date());
        msg.setText(text);

        // Send message directly to the queue
        for (int i=0; i< 1; i++) {

            MsgInfo msgCorrelationInfo = new MsgInfo(i);
            msgCorrelationInfo.sessionIndependentMessage = msg;
            msg.setCorrelationKey(msgCorrelationInfo);

            prod.send(msg, queue);
            System.out.println("Message sent. Exiting.");

        }
        // Delivery not yet confirmed. See ConfirmedPublish.java
        Thread.sleep(2000);

        // Close session
        session.closeSession();

    }

    public void testPTL() throws JCSMPException, JMSException, InterruptedException {
        // Check command line arguments

        System.out.println("QueueProducer initializing...");
        // Create a JCSMP Session
        final JCSMPProperties properties = new JCSMPProperties();
        properties.setProperty(JCSMPProperties.HOST, "localhost");     // host:port
        properties.setProperty(JCSMPProperties.USERNAME, "default"); // client-username
        properties.setProperty(JCSMPProperties.VPN_NAME,  "default"); // message-vpn
        properties.setProperty(JCSMPProperties.PASSWORD, "default"); // client-password
        JCSMPSession session = JCSMPFactory.onlyInstance().createSession(properties);

        session.connect();

        String topicName = "T/tutorial";
//        EndpointProperties endpointProps = new EndpointProperties();
//        endpointProps.setPermission(EndpointProperties.PERMISSION_CONSUME);
//        endpointProps.setAccessType(EndpointProperties.ACCESSTYPE_EXCLUSIVE);
        // create the queue object locally
        Topic topic = JCSMPFactory.onlyInstance().createTopic(topicName);
        // Actually provision it, and do not fail if it already exists
//        session.provision(queue, endpointProps, JCSMPSession.FLAG_IGNORE_ALREADY_EXISTS);

        /** Anonymous inner-class for handling publishing events */

        final XMLMessageProducer prod = session.getMessageProducer(new PubCallback());


//        final XMLMessageProducer prod = session.getMessageProducer(
//                new JCSMPStreamingPublishEventHandler() {
//                    @Override
//                    public void responseReceived(String messageID) {
//                        System.out.printf("Producer received response for msg ID #%s%n",messageID);
//                    }
//                    @Override
//                    public void handleError(String messageID, JCSMPException e, long timestamp) {
//                        System.out.printf("Producer received error for msg ID %s @ %s - %s%n",
//                                messageID,timestamp,e);
//                    }
//                });

        // Publish-only session is now hooked up and running!
        System.out.printf("Connected. About to send message to Topic '%s'...%n",topic.getName());

        TextMessage msg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
        msg.setDeliveryMode(DeliveryMode.PERSISTENT);
        String text = "Persistent Queue Tutorial! "+ DateFormat.getDateTimeInstance().format(new Date());
        msg.setText(text);

        // Send message directly to the queue
        for (int i=0; i< 3; i++) {

            MsgInfo msgCorrelationInfo = new MsgInfo(i);
//            msgCorrelationInfo.sessionIndependentMessage = msg;
            msg.setCorrelationKey(msgCorrelationInfo);

            prod.send(msg, topic);
            System.out.println("Message sent. Exiting.");

        }
        // Delivery not yet confirmed. See ConfirmedPublish.java
        Thread.sleep(2000);

        // Close session
        session.closeSession();

    }


}
