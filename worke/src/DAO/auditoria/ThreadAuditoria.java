package DAO.auditoria;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadAuditoria extends Thread {

    private boolean status;

    public void setStatus(boolean status){
        this.status = status;
    }

    @Override
    public void run() {
        setStatus(true);

        while(status) {
            try{
                String msg = Auditoria.getInstance().popMessage();
                if (msg != null) {
                    printMessage(msg);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadAuditoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void printMessage(String messsage) throws InterruptedException {
        System.out.printf("%s - Valor %s\n", Instant.now().toString(), messsage);
        Thread.sleep(300);
    }
}
