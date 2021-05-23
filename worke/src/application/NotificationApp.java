package application;

import comuns.acesso.Funcionario;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

public class NotificationApp {

    public static Timeline timeline = new Timeline();

    public static void startTimerNotification(double horas){
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
