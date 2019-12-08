package client;


import javax.jms.Message;

public class ProducerCompletionListener implements javax.jms.CompletionListener {
    @Override
    public void onCompletion(Message message) {
        System.out.println("onCompletion");

    }

    @Override
    public void onException(Message message, Exception exception) {
        System.out.println("onException");

    }
}
