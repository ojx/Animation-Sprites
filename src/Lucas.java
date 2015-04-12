import sprite.Sprite;

/**
 * A class for the sprite-lucas.png sprite-sheet
 */
public class Lucas extends Sprite {
    public Lucas() {
        super("sprite-lucas.png");

        createAnimation("running", 50);  //name, frame speed

        for (int i = 0; i < 8; i++) {
            addFrame("running", i * 40, 55, 40, 40); //name, x, y, width, height
        }

        createAnimation("standing", 200);    //name, frame speed

        for (int i = 0; i < 4; i++) {
            addFrame("standing", i * 40, 5, 40, 40); //name, x, y, width, height
        }

        createAnimation("jumping", 180);  //name, frame speed

        for (int i = 0; i < 9; i++) {
            addFrame("jumping", i * 40, 102, 40, 40); //name, x, y, width, height
        }
    }

    public void runLeft() {
        play("running");
        flipHorizontally(true);
        setAngle(270);
        setSpeed(2);
    }

    public void runRight() {
        play("running");
        flipHorizontally(false);
        setAngle(90);
        setSpeed(2);
    }

    public void stand() {
        play("standing");
        setSpeed(0);
    }

    public void jump() {

        if (isStanding()) {
            setAngle(0);
            setSpeed(3);
            applyGravity(2);
            play("jumping", "standing");
        } else if (isRunningLeft()) {
            setAngle(330);
            setSpeed(3);
            applyGravity(2);
            play("jumping", "running");
        } else if (isRunningRight()) {
            setAngle(30);
            setSpeed(3);
            applyGravity(2);
            play("jumping", "running");
        }

    }

    public boolean isStanding() {
        return getAnimationName().equals("standing");
    }

    public boolean isRunningLeft() {
        return getAnimationName().equals("running") && getAngle() == 270;
    }

    public boolean isRunningRight() {
        return getAnimationName().equals("running") && getAngle() == 90;
    }

}
