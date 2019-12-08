package coolcf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;

public class CoolInitialContextFactory implements InitialContextFactory {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Context getInitialContext(Hashtable<?, ?> env) throws NamingException {
    	Context initialContext = new CoolContext(env);
        return(initialContext);
    }

}