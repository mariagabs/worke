package DAO.auditoria;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuditoriaTest {

    private static AuditoriaTest instance;

    public static AuditoriaTest getInstance() {
        if (instance == null) {
            instance = new AuditoriaTest();
        }
        return instance;
    }

    public void StartThread(String action) throws InterruptedException{

        Auditoria.getInstance().threadOn();

        try{
            System.out.printf("%s - Clicked on %s\n\n", Instant.now().toString(), action);


        } catch (Exception ex) {
            Logger.getLogger(AuditoriaTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Auditoria.getInstance().threadOff();
        }

        //System.out.printf("%s - End\n\n", Instant.now().toString());
    }
}
