package com.example.pomodoro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    private boolean shortBreakOn, longBreakOn = false;
    private boolean pomoOn = true;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.time = new SettingsScreenController();
        initializeTimer();
    }
    private void initializeTimer() {
        timer.setText(String.format("%02d:%02d", time.getPomoMinutes() / 60, time.getPomoMinutes() % 60));
        shortBreakOn = longBreakOn = false;
        pomoOn = true;
    }
    public void startTimer() {
        int startTimeInSecs = 0;
        if (pomoOn) {startTimeInSecs = time.getPomoMinutes();}
        else if (shortBreakOn) {startTimeInSecs = time.getSmBreakMin();}
        else if (longBreakOn) {startTimeInSecs = time.getLgBreakMin();}

        final int[] timeArr = {startTimeInSecs};
        if (!timerOn) {
            if (timeline == null) {
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
            startTimer.setText("Pause");
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
        resetTimers();
        if (pomoOn) {timer.setText(String.format("%02d:%02d", time.getPomoMinutes() / 60, time.getPomoMinutes() % 60));}
        else if (shortBreakOn) {timer.setText(String.format("%02d:%02d", time.getSmBreakMin() / 60, time.getSmBreakMin() % 60));}
        else if (longBreakOn) {timer.setText(String.format("%02d:%02d", time.getLgBreakMin() / 60, time.getLgBreakMin() % 60));}
    }

    public void openSettings() {
    }

    public void setPomoTimer() {
        if (pomoOn) {return;}
        initializeTimer();
        resetTimers();
    }

    public void setSmBreakTimer() {
        if (shortBreakOn) {return;}
        timer.setText(String.format("%02d:%02d", time.getSmBreakMin() / 60, time.getSmBreakMin() % 60));
        pomoOn = longBreakOn = false;
        shortBreakOn = true;
        resetTimers();
    }

    public void setLgBreakTimer() {
        if (longBreakOn) {return;}
        timer.setText(String.format("%02d:%02d", time.getLgBreakMin() / 60, time.getLgBreakMin() % 60));
        pomoOn = shortBreakOn = false;
        longBreakOn = true;
        resetTimers();
    }
    private void resetTimers() {
        if (timeline != null) {
            timeline.stop();
        }
        timerOn = false;
        startTimer.setText("Start");
        timeline = null;
    }
}
