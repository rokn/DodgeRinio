package com.wordpress.amindov.dodgerinio;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio Mindov on 5/24/2016.
 */
public class Pattern {

    private class Entry {
        public Entry() {
            percentage = 0.0f;
            position = new PointF();
            velocity = new PointF();
            rotation = 0.0f;
        }

        public float percentage;
        public PointF position;
        public PointF velocity;
        public float rotation;
    }

    private List<Entry> entries;
    private List<Block> blocks;
    private float multiplier;

    public Pattern() {
        entries = new ArrayList<>();
        blocks = new ArrayList<>();
        multiplier = 1.0f;
    }

    public void addEntry(float percentage, PointF pos, PointF velocity, float rotation) {
        Entry newEntry = new Entry();
        newEntry.percentage = percentage;
        newEntry.position = pos;
        newEntry.velocity = velocity;
        newEntry.rotation = rotation;
        entries.add(newEntry);
    }

    public void play(State state) {
        for (Entry entry : entries) {
            PointF velocity = new PointF();
            velocity.x = entry.velocity.x * multiplier;
            velocity.y = entry.velocity.y * multiplier;
            Block block = new Block(entry.percentage, entry.position, velocity, entry.rotation);
            state.addGameObject(block);
            blocks.add(block);
        }
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    public boolean isDone() {
        boolean done = true;

        for (Block block : blocks) {
            done &= block.isDestroyed();
        }

        if(done) {
            blocks.clear();
        }

        return done;
    }
}