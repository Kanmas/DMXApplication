package com.example.quentinlehmann.dmxv2;

import java.util.ArrayList;

/**
 * Created by quentin.lehmann on 01/06/2018.
 */

public class Storyboard {
    private ArrayList<StoryboardElement> storyboardElements;
    private String name;

    public Storyboard () {
        storyboardElements = new ArrayList<>(  );
    }


    public Storyboard (String name) {
        storyboardElements = new ArrayList<>(  );
        this.name = name;
    }

    public ArrayList<StoryboardElement> getStoryboardElements() {
        return storyboardElements;
    }

    public void setStoryboardElements(ArrayList<StoryboardElement> storyboardElements) {
        this.storyboardElements = storyboardElements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration () {
        double time = 0.0;

        for (StoryboardElement e : storyboardElements) {
            time += e.getTime();
        }

        return time;
    }

    public double getCount () {
        return storyboardElements.size();
    }

    public static ArrayList<Storyboard> fake (int max) {
        ArrayList<Storyboard> fake = new ArrayList<>(  );

        for (int i = 0; i < max; i++) {
            fake.add(new Storyboard("Story board " + String.valueOf( i )));
        }

        return fake;
    }
}
