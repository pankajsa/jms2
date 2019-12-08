package coolcf;

import com.solacesystems.jcsmp.JCSMPFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Destination;

public class CoolDestination implements Destination{
    private com.solacesystems.jcsmp.Destination sDest;
    private boolean isQueue;
//    private Queue sQueue;
//    private char destType;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CoolDestination(){

    }

    protected com.solacesystems.jcsmp.Destination getSDestination(){
        return this.sDest;
    }

//    protected char getSDestinationType(){
//        return destType;
//    }

    protected boolean isQueue(){
        return isQueue;
    }
    protected void setSDestination(char destType, String destName){
//        this.destType = destType;
        logger.info("DestType: " + destType);
        if (destType == 'T') {
            sDest = JCSMPFactory.onlyInstance().createTopic(destName);
            isQueue = false;
        }
        else {
            sDest = JCSMPFactory.onlyInstance().createQueue(destName);
            isQueue = true;
        }
        logger.info(sDest.toString());

    }

}
