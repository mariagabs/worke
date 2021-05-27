package test;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.junit.Test;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

public class NotificationAppTest {

    public static Timeline timeline = new Timeline();

    @Test
    public void startTimerNotification(){
        double horas = 0;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.hours(horas),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {

                                TrayNotification tray = new TrayNotification();
                                Image img = new Image(getClass().getResource("/resources/img/notification.png").toExternalForm());
                                tray.setTray("Chegou a hora!", "Venha se exercitar com a gente!", img, Paint.valueOf("#A3D3E4"), AnimationType.POPUP);
                                tray.showAndWait();
                            }

                        }));

        timeline.playFromStart();
    }
}
