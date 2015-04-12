package sprite;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Sprite extends ImageView {
    private static int FRAME_SPEED = 30;

    private Timeline frameTimeLine, animationTimeLine;
    private double originalGravityFactor;
    private double gravityFactor;
    private double angle;
    private double speed;
    private double dx;
    private double dy;
    private ArrayList<AnimationFrames> animations;
    private AnimationFrames current;
    private LinkedList<AnimationFrames> playQueue;
    private ArrayList<Rectangle2D> platforms;  //rectangles that stop gravity

    protected Sprite(String image) {
        if (image != null) {
            current = null;
            playQueue = new LinkedList<>();
            angle = 0;
            speed = 0;
            dx = 0;
            dy = 0;
            gravityFactor = 1;
            originalGravityFactor = 0;
            setVisible(false);
            setImage(new Image(image));
            setViewport(new Rectangle2D(0, 0, 1, 1));

            animationTimeLine = new Timeline(new KeyFrame(new Duration(FRAME_SPEED), e -> tick()));
            animationTimeLine.setCycleCount(Animation.INDEFINITE);
            animationTimeLine.play();

            this.animations = new ArrayList<>();
            platforms = new ArrayList<>();
        }
    }


    protected void play(String... s) {
        playQueue.clear();

        if (s.length == 0)
            return;

        for (String name : s) {
            for (AnimationFrames a : animations) {
                if (a.getName().equalsIgnoreCase(name)) {
                    playQueue.add(a);
                }
            }
        }

        current = null;
        playNext();
    }

    public double getCenterX() {
        if (current == null)
            return getLayoutX();

        return getLayoutX() + current.getWidth() / 2;
    }

    public double getCenterY() {
        if (current == null)
            return getLayoutY();

        return getLayoutY() + current.getHeight() / 2;
    }

    public void setCenterX(double x) {
        if (current == null) {
            setLayoutX(x);
            return;
        }

        setLayoutX(x - current.getWidth() / 2);
    }

    public void setCenterY(double y) {
        if (current == null) {
            setLayoutY(y);
            return;
        }

        setLayoutX(y - current.getHeight() / 2);
    }


    public double getLeftX() {
        return getLayoutX();
    }

    public double getTopY() {
        return getLayoutY();
    }

    public void setLeftX(double x) {
        setLayoutX(x);
    }

    public void setTopY(double y) {
        setLayoutX(y);
    }

    public double getRightX() {
        if (current == null)
            return getLayoutX();

        return getLayoutX() + current.getWidth();
    }

    public double getBottomY() {
        if (current == null)
            return getLayoutY();

        return getLayoutY() + current.getHeight();
    }

    public void setRightX(double x) {
        if (current == null) {
            setLayoutX(x);
            return;
        }

        setLayoutX(x - current.getWidth());
    }

    private void playNext() {
        if (playQueue.size() == 0)
            return;

        current = playQueue.remove(0);

        if (frameTimeLine != null)
            frameTimeLine.stop();

        frameTimeLine = new Timeline(new KeyFrame(new Duration(current.getFs()), e -> nextFrame()));
        frameTimeLine.setCycleCount(Animation.INDEFINITE);
        frameTimeLine.play();

        if (playQueue.size() == 0)
            current.play(true);
        else
            current.play(false);
    }

    private void nextFrame() {
        setViewport(current.getFrame());
        if (current.isDone()) {
            current.reset();
            playNext();
        }
    }


    protected void applyGravity(double factor) {

        if (platforms.size() == 0) {
            if (getParent() instanceof Pane) {
                Pane pane = (Pane) getParent();
                platforms.add(new Rectangle2D(0,pane.getHeight() - 10,pane.getWidth(), 10));
            }
        }

        if (factor == 0) {
            gravityFactor = 0;
            originalGravityFactor = 0;
        } else if (factor != originalGravityFactor) {
            gravityFactor = factor;
            originalGravityFactor = factor;
        } else {
            gravityFactor *= factor * (1 + FRAME_SPEED / 400.0);
        }
        dy += gravityFactor * 0.01;

    }

    protected double getAngle() {
        return angle;
    }


    protected void setAngle(double angle) {
        this.angle = angle;
        updateDeltas();
    }

    public double getSpeed() {
        return speed;
    }

    protected void setSpeed(double speed) {
        this.speed = speed;
        updateDeltas();
    }

    public void stop() {
        playQueue.clear();
        current = null;
    }

    /*
    Method to adjust the position on the sprite
     */
    private void tick() {
        double origY = getBottomY();
        setLayoutX(getLayoutX() + dx);
        setLayoutY(getLayoutY() + dy);

        if (getImage().getProgress() >= 1) {
            setVisible(true);
        }

        if (getParent() instanceof Pane) {
            Pane pane = (Pane)getParent();

            if ((this.getLayoutX() > pane.getWidth() - this.getWidth()) || (this.getLayoutX() < 0)) {  //change direction at edge
                this.setAngle(360 - this.getAngle());

                if (this.isFlippedHorizontally())
                    this.flipHorizontally(false);
                else
                    this.flipHorizontally(true);
            }

            if (platforms.size() > 0 && gravityFactor > 0) {
                for (Rectangle2D p : platforms) {
                    if (this.getCenterX() > p.getMinX() && this.getCenterX() < p.getMaxX()) {
                        if (origY < p.getMinY() && getBottomY() > p.getMinY()) {
                            this.applyGravity(0);
                            this.setSpeed(0);
                            this.setLayoutY(p.getMinY() - this.getHeight());
                            playNext();
                        } else {
                            this.applyGravity(1);
                        }
                    }
                }
            }
        }

    }

    private void updateDeltas() {
        dx = speed * Math.cos(Math.toRadians(angle - 90));
        dy = speed * Math.sin(Math.toRadians(angle - 90));
    }

    public void flipHorizontally(boolean flipped) {
        if (flipped)
            setScaleX(-1);
        else
            setScaleX(1);
    }

    public void flipVertically(boolean flipped) {
        if (flipped)
            setScaleY(-1);
        else
            setScaleY(1);
    }

    public boolean isFlippedHorizontally() {
        return getScaleX() < 0;
    }

    public boolean isFlippedVertically() {
        return getScaleY() < 0;
    }

    public double getWidth() {
        return getLayoutBounds().getWidth();
    }

    public double getHeight() {
        return getLayoutBounds().getHeight();
    }

    protected String getAnimationName() {
        return current == null ? null : current.getName();
    }

    protected void createAnimation(String name, long fs)  {
        //check if name already used
        for (AnimationFrames a : animations) {
            if (a.getName().equalsIgnoreCase(name)) {
               return;
            }
        }

        animations.add(new AnimationFrames(name, fs));
    }

    protected boolean addFrame(String name, int x, int y, int width, int height) {
        for (AnimationFrames a : animations) {
            if (a.getName().equalsIgnoreCase(name)) {
                a.add(new Rectangle2D(x, y, width, height));

                return true;
            }
        }
        return false;
    }

    public void addPlatform(double x, double y, double width, double height) {
        platforms.add(new Rectangle2D(x, y, width, height));
    }

}
