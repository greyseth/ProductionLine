package model;

import enums.ItemType;

public class AudioPlayer extends Product implements MultimediaControls{
    String audioSpecification;
    ItemType mediaType;

    public AudioPlayer(String name, String audioSpecification) {
        super(name);
        this.audioSpecification = audioSpecification;
        mediaType = ItemType.AU;
    }

    @Override
    public void play() {
        System.out.println("Playing Audio");
    }

    @Override
    public void stop() {
        System.out.println("Audio Stopped");
    }

    @Override
    public void previous() {
        System.out.println("Going back");
    }

    @Override
    public void next() {
        System.out.println("Going forward");
    }

    @Override
    public String toString() {
        return super.toString()+"\nAudio Specification: "+audioSpecification+"\nMedia Type : "+mediaType;
    }
}
