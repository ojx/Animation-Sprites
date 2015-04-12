package sprite;

import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class AnimationFrames {
    private String name;
    private long fs;
    private int currentFrame;
    private ArrayList<Rectangle2D> frames;
    private boolean repeat;
    private boolean done;

    public AnimationFrames(String name, long fs) {
        this.name = name;
        currentFrame = 0;
        this.fs = fs;
        frames = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public long getFs() {
        return fs;
    }

    public double getWidth() {
        if (currentFrame < 0 || currentFrame >= frames.size())
            return 0;

        return frames.get(currentFrame).getWidth();
    }

    public double getHeight() {
        if (currentFrame < 0 || currentFrame >= frames.size())
            return 0;

        return frames.get(currentFrame).getHeight();
    }

    public void play(boolean repeat) {
        //   System.out.println("Playing: "+name+" - Repeat: "+ repeat + " - Frames: " + frames.size());
        currentFrame = 0;
        this.repeat = repeat;
    }

    public boolean isDone() {
        return done;
    }

    public void add(Rectangle2D r) {
        this.frames.add(r);
    }

    public Rectangle2D getFrame() {
        Rectangle2D frame = null;

        if (repeat && currentFrame >= frames.size()) {
            currentFrame = 0;
            frame = frames.get(currentFrame);
        } else if (currentFrame < frames.size()) {
            frame = frames.get(currentFrame);
            currentFrame++;
        } else {
            frame = frames.get(frames.size()-1);
            done = true;
        }

        return frame;
    }

    public void reset() {
        this.currentFrame = 0;
        this.done = false;
    }
}
