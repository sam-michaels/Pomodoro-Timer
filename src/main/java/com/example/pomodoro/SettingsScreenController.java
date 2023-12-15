package com.example.pomodoro;

public class SettingsScreenController {
    private int pomoMinutes;
    private int smBreakMin;
    private int lgBreakMin;
    public SettingsScreenController() {
        this.pomoMinutes = 25 * 60;
        this.smBreakMin = 5 * 60;
        this.lgBreakMin = 10 * 60;
    }
    public int getPomoMinutes() {
        return pomoMinutes;
    }

    public void setPomoMinutes(int minutes) {
        this.pomoMinutes = minutes * 60;
    }

    public int getSmBreakMin() {
        return smBreakMin;
    }

    public void setSmBreakMin(int smBreakMin) {
        this.smBreakMin = smBreakMin;
    }

    public int getLgBreakMin() {
        return lgBreakMin;
    }

    public void setLgBreakMin(int lgBreakMin) {
        this.lgBreakMin = lgBreakMin;
    }
}
