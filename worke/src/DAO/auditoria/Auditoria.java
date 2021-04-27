package DAO.auditoria;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Auditoria {

    ConcurrentLinkedQueue<String> messagesQueue;

    private static Auditoria instance;

    private Auditoria() {
        messagesQueue = new ConcurrentLinkedQueue<String>();
    }

    public static Auditoria getInstance() {
        if (instance == null) {
            instance = new Auditoria();
        }
        return instance;
    }

    ThreadAuditoria thread;

    public void addMessage(String message) {
        messagesQueue.add(message);
    }

    String popMessage() {
        String message = messagesQueue.poll();
        return message;
    }

    void threadOn() {
        if (thread == null){
            thread = new ThreadAuditoria();
            thread.start();
        }
    }

    void threadOff(){
        if (thread != null) {
            thread.setStatus(false);
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Auditoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (thread.isAlive())
                thread.interrupt();
        }
    }
}
