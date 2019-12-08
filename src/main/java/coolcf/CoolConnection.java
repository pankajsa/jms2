package coolcf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.lang.IllegalStateException;
import java.util.Hashtable;

public class CoolConnection implements Connection{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Session session;
    private Hashtable<?,?> env;

    public CoolConnection(String username, String password) {
        logger.debug("CoolConnection :" +  username + ":" + password);

    }


    protected CoolConnection(Hashtable<?,?> env) {
        this.env = env;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Session createSession(boolean isTransacted, int ackMode) throws JMSException {
        logger.debug("CoolConnection createSession");
        session = new CoolSession(env, isTransacted, ackMode);
        return(session);
    }

    /**
     * Creates a {@code Session} object, specifying {@code sessionMode}.
     * <p>
     * The effect of setting the {@code sessionMode}
     * argument depends on whether this method is called in a Java SE environment,
     * in the Java EE application client container, or in the Java EE web or EJB container.
     * If this method is called in the Java EE web or EJB container then the
     * effect of setting the {@code sessionMode} argument also depends on
     * whether or not there is an active JTA transaction in progress.
     * <p>
     * In a <b>Java SE environment</b> or in <b>the Java EE application client container</b>:
     * <ul>
     * <li>If {@code sessionMode} is set to {@code Session.SESSION_TRANSACTED} then the session
     * will use a local transaction which may subsequently be committed or rolled back
     * by calling the session's {@code commit} or {@code rollback} methods.
     * <li>If {@code sessionMode} is set to any of
     * {@code Session.CLIENT_ACKNOWLEDGE},
     * {@code Session.AUTO_ACKNOWLEDGE} or
     * {@code Session.DUPS_OK_ACKNOWLEDGE}.
     * then the session will be non-transacted and
     * messages received by this session will be acknowledged
     * according to the value of {@code sessionMode}.
     * For a definition of the meaning of these acknowledgement modes see the links below.
     * </ul>
     * <p>
     * In a <b>Java EE web or EJB container, when there is an active JTA transaction in progress</b>:
     * <ul>
     * <li>The argument {@code sessionMode} is ignored.
     * The session will participate in the JTA transaction and will be committed or rolled back
     * when that transaction is committed or rolled back,
     * not by calling the session's {@code commit} or {@code rollback} methods.
     * Since the argument is ignored, developers are recommended to use
     * {@code createSession()}, which has no arguments, instead of this method.
     * </ul>
     * <p>
     * In the <b>Java EE web or EJB container, when there is no active JTA transaction in progress</b>:
     * <ul>
     * <li>
     * If {@code sessionMode} is set to {@code Session.AUTO_ACKNOWLEDGE} or
     * {@code Session.DUPS_OK_ACKNOWLEDGE} then the session will be
     * non-transacted and messages will be acknowledged according to the value
     * of {@code sessionMode}.
     * <li>
     * If {@code sessionMode} is set to {@code Session.CLIENT_ACKNOWLEDGE} then the JMS
     * provider is recommended to ignore the specified parameter and instead
     * provide a non-transacted, auto-acknowledged session. However the JMS
     * provider may alternatively provide a non-transacted session with
     * client acknowledgement.
     * <li>
     * If {@code sessionMode} is set to {@code Session.SESSION_TRANSACTED}, then the JMS
     * provider is recommended to ignore the specified parameter and instead
     * provide a non-transacted, auto-acknowledged session. However the JMS
     * provider may alternatively provide a local transacted session.
     * <li>
     * Applications are recommended to use only the values
     * {@code Session.AUTO_ACKNOWLEDGE} and {@code Session.DUPS_OK_ACKNOWLEDGE}
     * since applications which use {@code Session.CLIENT_ACKNOWLEDGE} or
     * {@code Session.SESSION_TRANSACTED} may not be portable.
     * </ul>
     * <p>
     * Applications running in the Java EE web and EJB containers must not attempt
     * to create more than one active (not closed) {@code Session} object per connection.
     * If this method is called in a Java EE web or EJB container when an active
     * {@code Session} object already exists for this connection then a {@code JMSException} may be thrown.
     *
     * @param sessionMode specifies the session mode that will be used, except in the
     *                    cases described above when this value is ignored. Legal values are
     *                    {@code JMSContext.SESSION_TRANSACTED}, {@code JMSContext.CLIENT_ACKNOWLEDGE},
     *                    {@code JMSContext.AUTO_ACKNOWLEDGE} and {@code JMSContext.DUPS_OK_ACKNOWLEDGE}.
     * @return a newly created  session
     * @throws JMSException if the {@code Connection} object fails
     *                      to create a session due to
     *                      <ul>
     *                      <li>some internal error,
     *                      <li>lack of support for the specific transaction and acknowledgement mode, or
     *                      <li>because this method is being called in a Java EE web or EJB application
     *                      and an active session already exists for this connection.
     *                      </ul>
     * @see Session#SESSION_TRANSACTED
     * @see Session#AUTO_ACKNOWLEDGE
     * @see Session#CLIENT_ACKNOWLEDGE
     * @see Session#DUPS_OK_ACKNOWLEDGE
     * @see Connection#createSession(boolean, int)
     * @see Connection#createSession()
     * @since JMS 2.0
     */
    @Override
    public Session createSession(int sessionMode) throws JMSException {
        return null;
    }

    /**
     * Creates a {@code Session} object,
     * specifying no arguments.
     * <p>
     * The behaviour of the session that is created depends on
     * whether this method is called in a Java SE environment,
     * in the Java EE application client container, or in the Java EE web or EJB container.
     * If this method is called in the Java EE web or EJB container then the
     * behaviour of the session also depends on whether or not
     * there is an active JTA transaction in progress.
     * <p>
     * In a <b>Java SE environment</b> or in <b>the Java EE application client container</b>:
     * <ul>
     * <li>The session will be non-transacted and received messages will be acknowledged automatically
     * using an acknowledgement mode of {@code Session.AUTO_ACKNOWLEDGE}
     * For a definition of the meaning of this acknowledgement mode see the link below.
     * </ul>
     * <p>
     * In a <b>Java EE web or EJB container, when there is an active JTA transaction in progress</b>:
     * <ul>
     * <li>The session will participate in the JTA transaction and will be committed or rolled back
     * when that transaction is committed or rolled back,
     * not by calling the session's {@code commit} or {@code rollback} methods.
     * </ul>
     * <p>
     * In the <b>Java EE web or EJB container, when there is no active JTA transaction in progress</b>:
     * <ul>
     * <li>The session will be non-transacted and received messages will be acknowledged automatically
     * using an acknowledgement mode of {@code Session.AUTO_ACKNOWLEDGE}
     * For a definition of the meaning of this acknowledgement mode see the link below.
     * </ul>
     * <p>
     * Applications running in the Java EE web and EJB containers must not attempt
     * to create more than one active (not closed) {@code Session} object per connection.
     * If this method is called in a Java EE web or EJB container when an active
     * {@code Session} object already exists for this connection then a {@code JMSException} may be thrown.
     *
     * @return a newly created  session
     * @throws JMSException if the {@code Connection} object fails
     *                      to create a session due to
     *                      <ul>
     *                      <li>some internal error or
     *                      <li>because this method is being called in a Java EE web or EJB application
     *                      and an active session already exists for this connection.
     *                      </ul>
     * @see Session#AUTO_ACKNOWLEDGE
     * @see Connection#createSession(boolean, int)
     * @see Connection#createSession(int)
     * @since JMS 2.0
     */
    @Override
    public Session createSession() throws JMSException {
        return null;
    }

    @Override
    public String getClientID() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setClientID(String s) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setExceptionListener(ExceptionListener exceptionListener) throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void start() throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void stop() throws JMSException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void close() throws JMSException {
        session.close();

    }

    @Override
    public ConnectionConsumer createConnectionConsumer(Destination destination, String s, ServerSessionPool serverSessionPool, int i) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Creates a connection consumer for this connection (optional operation)
     * on the specific topic using a shared non-durable subscription with
     * the specified name.
     * <p>
     * This is an expert facility not used by ordinary JMS clients.
     * <p>
     * This method must not be used in a Java EE web or EJB application. Doing
     * so may cause a {@code JMSException} to be thrown though this is not
     * guaranteed.
     *
     * @param topic            the topic to access
     * @param subscriptionName the name used to identify the shared non-durable subscription
     * @param messageSelector  only messages with properties matching the message selector
     *                         expression are delivered. A value of null or an empty string
     *                         indicates that there is no message selector for the message
     *                         consumer.
     * @param sessionPool      the server session pool to associate with this connection
     *                         consumer
     * @param maxMessages      the maximum number of messages that can be assigned to a
     *                         server session at one time
     * @return the connection consumer
     * @throws IllegalStateException       if called on a {@code QueueConnection}
     * @throws InvalidDestinationException if an invalid destination is specified.
     * @throws InvalidSelectorException    if the message selector is invalid.
     * @throws JMSException                if the {@code Connection} object fails to create a
     *                                     connection consumer for one of the following reasons:
     *                                     <ul>
     *                                     <li>an internal error has occurred
     *                                     <li>invalid arguments for {@code sessionPool} and
     *                                     {@code messageSelector} or
     *                                     <li>this method has been called in a Java EE web or EJB
     *                                     application (though it is not guaranteed that an exception
     *                                     is thrown in this case)
     *                                     </ul>
     * @see ConnectionConsumer
     * @since JMS 2.0
     */
    @Override
    public ConnectionConsumer createSharedConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return null;
    }

    @Override
    public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String s, String s1, ServerSessionPool serverSessionPool, int i) throws JMSException {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Creates a connection consumer for this connection (optional operation)
     * on the specific topic using a shared durable subscription with
     * the specified name.
     * <p>
     * This is an expert facility not used by ordinary JMS clients.
     * <p>
     * This method must not be used in a Java EE web or EJB application. Doing
     * so may cause a {@code JMSException} to be thrown though this is not
     * guaranteed.
     *
     * @param topic            topic to access
     * @param subscriptionName the name used to identify the shared durable subscription
     * @param messageSelector  only messages with properties matching the message selector
     *                         expression are delivered. A value of null or an empty string
     *                         indicates that there is no message selector for the message
     *                         consumer.
     * @param sessionPool      the server session pool to associate with this durable
     *                         connection consumer
     * @param maxMessages      the maximum number of messages that can be assigned to a
     *                         server session at one time
     * @return the durable connection consumer
     * @throws IllegalStateException       if called on a {@code QueueConnection}
     * @throws InvalidDestinationException if an invalid destination is specified.
     * @throws InvalidSelectorException    if the message selector is invalid.
     * @throws JMSException                if the {@code Connection} object fails to create a
     *                                     connection consumer for one of the following reasons:
     *                                     <ul>
     *                                     <li>an internal error has occurred
     *                                     <li>invalid arguments
     *                                     for {@code sessionPool} and {@code messageSelector} or
     *                                     <li>this method has been called in a Java EE web or EJB
     *                                     application (though it is not guaranteed that an exception
     *                                     is thrown in this case)
     *                                     </ul>
     * @see ConnectionConsumer
     * @since JMS 2.0
     */
    @Override
    public ConnectionConsumer createSharedDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return null;
    }
}