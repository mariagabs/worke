package DAO.auditoria;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuditoriaTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.printf("%s - Start\n\n", Instant.now().toString());

        Auditoria.getInstance().threadOn();

        try{

            for (int i=0;i<10;i++){
                Random rand = new Random();
                int max = 1000;
                int next = rand.nextInt(max);
                String message = String.format("%s - Test Message %d\n\n", Instant.now().toString(),i+1);
                System.out.printf(message);
                Auditoria.getInstance().addMessage("Auditoria - "+ message);
                Thread.sleep(next);
            }

        } catch (Exception ex) {
            Logger.getLogger(AuditoriaTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Auditoria.getInstance().threadOff();
        }

        System.out.printf("%s - End\n\n", Instant.now().toString());
    }
}
