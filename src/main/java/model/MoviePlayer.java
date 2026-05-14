package model;

import enums.MonitorType;

public class MoviePlayer extends Product implements MultimediaControls{
    Screen screen;
    MonitorType monitorType;

    public MoviePlayer(String name, Screen screen, MonitorType monitorType) {
        super(name);
        this.screen = screen;
        this.monitorType = monitorType;
    }

    @Override
    public void play() {
        System.out.println("Playing movie");
    }

    @Override
    public void stop() {
        System.out.println("Movie stopped");
    }

    @Override
    public void previous() {
        System.out.println("Previous movie");
    }

    @Override
    public void next() {
        System.out.println("Next movie");
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+screen.toString()+"\nMonitor Type : "+monitorType;
    }
}
