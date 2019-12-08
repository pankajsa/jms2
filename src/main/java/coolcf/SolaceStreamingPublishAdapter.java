package coolcf;

import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPStreamingPublishCorrelatingEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.CompletionListener;

public class SolaceStreamingPublishAdapter implements JCSMPStreamingPublishCorrelatingEventHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private CompletionListener completionListener;

    public SolaceStreamingPublishAdapter(CompletionListener completionListener) {
            this.completionListener = completionListener;
    }

    public SolaceStreamingPublishAdapter() {

    }


    @Override
    public void responseReceivedEx(Object object) {
        logger.debug("responseReceivedEx");

//        if (object instanceof CoolProducerOLd.MsgInfo) {
//            CoolProducerOLd.MsgInfo i = (CoolProducerOLd.MsgInfo) object;
//            i.acked = true;
//            i.publishedSuccessfully = true;
//            System.out.printf("response (accepted) received for %s \n", i);
//        }
        if (this.completionListener != null)
                this.completionListener.onCompletion(null);

    }

    @Override
    public void handleErrorEx(Object object, JCSMPException cause, long timestamp) {
        logger.info("handleErrorEx");

//        if (object instanceof CoolProducerOLd.MsgInfo) {
//            CoolProducerOLd.MsgInfo i = (CoolProducerOLd.MsgInfo) object;
//            i.acked = true;
//            System.out.printf("received for %s, error was %s \n", i, cause);
//        }
        if (this.completionListener != null)
            this.completionListener.onException(null,null);

    }

    @Override
    public void handleError(String messageID, JCSMPException e, long timestamp) {
        // Never called
    }

    @Override
    public void responseReceived(String messageID) {
        // Never called
    }
}
