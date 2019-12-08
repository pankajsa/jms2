import org.junit.jupiter.api.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

//import com.solacesystems.jcsmp.JCSMPProperties;


class JNDITest {

    @BeforeAll
    static void initAll() {
        System.out.println("SolJMSHelloWorldPub initializing...");

        // The client needs to specify all of the following properties:
        Hashtable<String, Object> env = new Hashtable<String, Object>();
//        env.put(InitialContext.INITIAL_CONTEXT_FACTORY, "coolcf.CoolInitialContextFactory");
        env.put(InitialContext.INITIAL_CONTEXT_FACTORY, "com.solacesystems.jndi.SolJNDIInitialContextFactory");


        env.put(InitialContext.PROVIDER_URL, "localhost");
        env.put("Solace_JMS_VPN", "default");
        env.put(Context.SECURITY_PRINCIPAL, "default");


        // InitialContext is used to lookup the JMS administered objects.
        try {
            InitialContext initialContext = new InitialContext(env);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        System.out.println("Environment==========");
        System.out.println(env);
        System.out.println(env.get(InitialContext.INITIAL_CONTEXT_FACTORY));
        System.out.println("Environment==========");



    }

    @BeforeEach
    void init() {
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}