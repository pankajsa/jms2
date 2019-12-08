/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package client;

import coolcf.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

class HelloWorld implements Serializable{
    private String name;
    public HelloWorld(String name){
        this.name = name;
    }

}


public class App {
    static Logger logger = LoggerFactory.getLogger("App");
    private InitialContext initialContext;
    private ConnectionFactory cf;

    public static void main(String[] args) throws Exception {
        logger.debug("main");

//        Sample sample = new Sample();
//        sample.testPTL();
//        sample.testPQL();
        App app = new App();
        app.setup();
//        app.publishToTopic();
        app.publishToTopic2();

//        app.publishToQueue();
//        app.subscribeTopic();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        app.cleanup();

    }



    public  void setup() throws Exception {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(InitialContext.INITIAL_CONTEXT_FACTORY, "coolcf.CoolInitialContextFactory");
        env.put(Constants.HOST, "localhost");     // host:port
        env.put(Constants.USERNAME, "default"); // client-username
        env.put(Constants.VPN_NAME,  "default"); // message-vpn
        env.put(Constants.PASSWORD, "default"); // client-password

        initialContext = new InitialContext(env);
        cf = (ConnectionFactory)initialContext.lookup("cf:myCF");

    }


    public void cleanup() throws Exception{
        initialContext.close();

    }
    public  void publishToTopic() throws Exception{
        // JMS Connection
        Connection connection = cf.createConnection();

        // Create a non-transacted, Auto Ack session.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Lookup Topic.
        Destination destination = (Destination)initialContext.lookup("t:a/b");

        // From the session, create a producer for the destination.
        // Use the default delivery mode as set in the connection factory
        MessageProducer producer = session.createProducer(destination);

        // Create a text message.
        TextMessage testMessage = session.createTextMessage("Hello world!");

        producer.send(testMessage, 0, 0, 0L, new ProducerCompletionListener());

        logger.debug("Message sent. Exiting.");
        connection.close();
    }

    public  void publishToTopic2() throws Exception{
        JMSContext context = cf.createContext(null,null,JMSContext.CLIENT_ACKNOWLEDGE);
        Destination destination = (Destination)initialContext.lookup("q:Q/tutorial");
        JMSProducer producer = context.createProducer();

        HashMap<String,Object> hm = new HashMap<String,Object>();
        hm.put("One","First Value");

        producer
//                .send(destination, new HelloWorld("Hello at " + new Date()))
//                .send(destination, ("Hello at " + new Date()).getBytes())
                .send(destination, (Map<String, Object>) hm)
//                .send(destination,"Hello at " + new Date())
        ;

        logger.debug("Message sent. Exiting.");
    }

    public  void publishToQueue() throws Exception{
        // JMS Connection
        Connection connection = cf.createConnection();

        // Create a non-transacted, Auto Ack session.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Create a text message.
        TextMessage testMessage = session.createTextMessage("Hello world!");

        // Lookup Topic.
        Destination destination = (Destination)initialContext.lookup("q:Q/tutorial");

        // From the session, create a producer for the destination.
        MessageProducer producer = session.createProducer(destination);

        producer.send(testMessage, 0, 0, 0L, new ProducerCompletionListener());



//        producer.send(testMessage);
        logger.debug("Message sent. Exiting.");
        connection.close();
    }


    private void subscribeTopic() throws JMSException, NamingException {
        // JMS Connection
        Connection connection = cf.createConnection();

        // Create a non-transacted, Auto Ack session.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Lookup Topic.
        Destination destination = (Destination)initialContext.lookup("t:a/b");

        // From the session, create a producer for the destination.
        // Use the default delivery mode as set in the connection factory
        MessageConsumer consumer = session.createConsumer(destination,null, false);

        consumer.setMessageListener(new ConsumerListener());

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        connection.close();

    }




}
