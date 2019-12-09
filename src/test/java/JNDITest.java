import coolcf.Constants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.fail;

//import com.solacesystems.jcsmp.JCSMPProperties;


class JNDITest {
    private InitialContext initialContext;
    private ConnectionFactory cf;

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() throws NamingException {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(InitialContext.INITIAL_CONTEXT_FACTORY, "coolcf.CoolInitialContextFactory");
        env.put(Constants.HOST, "localhost");     // host:port
        env.put(Constants.USERNAME, "default"); // client-username
        env.put(Constants.VPN_NAME,  "jmstest"); // message-vpn
        env.put(Constants.PASSWORD, "default2"); // client-password
        env.put(Constants.PUB_ACK_WINDOW_SIZE, 200);

        initialContext = new InitialContext(env);
        cf = (ConnectionFactory)initialContext.lookup("cf:myCF");

    }

    @Test
    void succeedingTest() {
    }

    @Test
    public void test() throws Exception{
        try{
            throw new RuntimeException();
        }
        catch (Exception ex){
            fail("Should not have thrown any exception");

        }
    }

    @Test
    void publishTopic2() throws Exception {
        JMSContext context = cf.createContext(null,null,JMSContext.CLIENT_ACKNOWLEDGE);
//        Destination destination = (Destination)initialContext.lookup("q:Q/tutorial");
//        JMSProducer producer = context.createProducer();
//
//        HashMap<String,Object> hm = new HashMap<String,Object>();
//        hm.put("One","First Value");
//
//        producer
////                .send(destination, new HelloWorld("Hello at " + new Date()))
////                .send(destination, ("Hello at " + new Date()).getBytes())
//                .send(destination, (Map<String, Object>) hm)
////                .send(destination,"Hello at " + new Date())
//        ;
//        Thread.sleep(1000);
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
//        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}