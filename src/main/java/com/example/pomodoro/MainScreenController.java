package com.example.pomodoro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private Label timer;
    @FXML
    private Button startTimer;
    private SettingsScreenController time;
    private boolean timerOn = false;
    private Timeline timeline;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.time = new SettingsScreenController();
        initializeTimer();
    }
    private void initializeTimer() {
        timer.setText(String.format("%02d:%02d", time.getPomoMinutes() / 60, time.getPomoMinutes() % 60));
    }
    public void startTimer() {

        int startTimeInSecs = time.getPomoMinutes();
        final int[] timeArr = {startTimeInSecs};
        if (!timerOn) {
            if (timeline == null) {
                startTimer.setText("Pause");
                timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    timeArr[0] --;
                    int min = timeArr[0] / 60;
                    int secs = timeArr[0] % 60;
                    timer.setText(String.format("%02d:%02d", min, secs));

                    if (timeArr[0] <= 0) {
                        restartTimer();
                    }
                }));
            }
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            timerOn = true;
        }
        else {
            timeline.pause();
            timerOn = false;
            startTimer.setText("Start");
        }
    }

    public void restartTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timerOn = false;
        startTimer.setText("Start");
        timer.setText(String.format("%02d:%02d", time.getPomoMinutes() / 60, time.getPomoMinutes() % 60));
        timeline = null;
    }

    public void openSettings() {
    }
}
