package coolcf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.*;
import java.io.Serializable;
import java.util.Hashtable;


public class CoolContext implements Context, Serializable {
    private Hashtable<?,?> env;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public CoolContext(Hashtable<?,?> env) {
        this.env = env;
    }

    @Override
    public Object lookup(Name name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public Object lookup(String name) throws NamingException {
        logger.debug("lookup(String):" + name);
        try {
            if (name.startsWith("cf:")){
                CoolConnectionFactory cf = (CoolConnectionFactory)Class.forName("coolcf.CoolConnectionFactory").newInstance();
                cf.setEnv(env);
                return(cf);
            }
            else if (name.startsWith("t:")){

                CoolDestination d =  (CoolDestination)Class.forName("coolcf.CoolDestination").newInstance();
                d.setSDestination('T', name.substring(2));
                return d;
            }
            else if (name.startsWith("q:")){
                CoolDestination d =  (CoolDestination)Class.forName("coolcf.CoolDestination").newInstance();
                d.setSDestination('Q', name.substring(2));
                return d;
            }
            throw new NamingException(name);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void bind(Name name, Object obj) throws NamingException {
        // TODO Auto-generated method stub
        throw new NamingException("Not Implemented");

    }

    @Override
    public void bind(String name, Object obj) throws NamingException {
        throw new NamingException("Not Implemented");

    }

    @Override
    public void rebind(Name name, Object obj) throws NamingException {
        throw new NamingException("Not Implemented");

    }

    @Override
    public void rebind(String name, Object obj) throws NamingException {
        throw new NamingException("Not Implemented");

    }

    @Override
    public void unbind(Name name) throws NamingException {
        throw new NamingException("Not Implemented");

    }

    @Override
    public void unbind(String name) throws NamingException {
        throw new NamingException("Not Implemented");

    }

    @Override
    public void rename(Name oldName, Name newName) throws NamingException {
        throw new NamingException("Not Implemented");

    }

    @Override
    public void rename(String oldName, String newName) throws NamingException {
        throw new NamingException("Not Implemented");

    }

    @Override
    public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
        logger.debug("listBindings(Name)");
        return null;
    }

    @Override
    public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public void destroySubcontext(Name name) throws NamingException {
        throw new NamingException("Not Implemented");

    }

    @Override
    public void destroySubcontext(String name) throws NamingException {
        logger.debug("destroySubcontext(String)");

    }

    @Override
    public Context createSubcontext(Name name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public Context createSubcontext(String name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public Object lookupLink(Name name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public Object lookupLink(String name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public NameParser getNameParser(Name name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public NameParser getNameParser(String name) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public Name composeName(Name name, Name prefix) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public String composeName(String name, String prefix) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public Object removeFromEnvironment(String propName) throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public Hashtable<?, ?> getEnvironment() throws NamingException {
        throw new NamingException("Not Implemented");
    }

    @Override
    public void close() throws NamingException {
        logger.debug("CoolContext:close - @TODO");
    }

    @Override
    public String getNameInNamespace() throws NamingException {
        throw new NamingException("Not Implemented");
    }
}
