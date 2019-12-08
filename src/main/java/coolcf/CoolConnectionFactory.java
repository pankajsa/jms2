package coolcf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import java.util.Hashtable;

public class CoolConnectionFactory implements ConnectionFactory{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Hashtable<?,?> env;
    @Override
    public Connection createConnection() throws JMSException {
        logger.debug("CoolConnectionFactory createConnection");

        Connection conn = new CoolConnection(env);
        return conn;
    }

    @Override
    public Connection createConnection(String username, String password) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public JMSContext createContext() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSContext createContext(String userName, String password) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public JMSContext createContext(String userName, String password, int sessionMode) {
        return new CoolJMSContext(this.env, sessionMode);
    }

    @Override
    public JMSContext createContext(int sessionMode) {
        throw new RuntimeException("Not Implemented");
    }

    protected void setEnv(Hashtable<?,?> env) {
        this.env = env;
    }
}