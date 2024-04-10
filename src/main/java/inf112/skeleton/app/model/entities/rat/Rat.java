package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.IEntity;
import inf112.skeleton.app.model.entities.Projectile;
import java.util.ArrayList;
import java.util.EnumMap;

public abstract class Rat implements IEntity {
    private int speed;
    private Vector2 pos;
    private int health;
    private Rectangle spriteRect;
    private float secs;
    private Integer bounty;
    private Integer points;
    private boolean rewardClaimed = false;
    private boolean exited = false;
    public float coinVisibleTime = 0f;
    private Sprite sprite;


    private boolean isFrozen;
    public ImageSwapper currentState = ImageSwapper.ALIVE;
    private EnumMap<ImageSwapper, Texture> textures = new EnumMap<>(ImageSwapper.class);

    public Rat(int health, int speed, Texture texture, Integer bounty, Integer points, Texture frozenTexture) {
        this.health = health;
        this.speed = speed;
        this.points = points;
        this.bounty = bounty;
        this.pos = new Vector2(-10, 430);
        this.sprite = new Sprite(texture);
        int halfsize = 40;
        this.sprite.setSize(halfsize * 2, halfsize * 2);
        this.sprite.setPosition(pos.x - halfsize, pos.y - halfsize);
        this.secs = 0;
        textures.put(ImageSwapper.ALIVE, texture);
        textures.put(ImageSwapper.FROZEN, frozenTexture);
        textures.put(ImageSwapper.DEAD, new Texture(Gdx.files.internal("coin.png")));
        this.spriteRect = new Rectangle(pos.x - halfsize, pos.y - halfsize, halfsize * 2, halfsize * 2);
    }
    

    /**
     * Checks if the reward has been claimed.
     * 
     * @return true if the reward has been claimed, false otherwise.
     */
    public boolean isrewardClaimed() {
        return rewardClaimed;
    }

    /**
     * Checks if the rat has exited the game.
     * @return true if the rat has exited the game, false otherwise.
     */
    public boolean isExited() {
        return exited;
    }

    /**
     * Marks the reward as claimed.
     */
    public void rewardClaimed() {
        this.rewardClaimed = true;
    }

    /**
     * Marks the rat as exited.
     */
    public void exit() {
        this.exited = true;
    }

    private enum ImageSwapper {
        ALIVE,
        FROZEN,
        DEAD;
    }

    /**
     * Checks if the rat is hit by any of the given projectiles.
     * 
     * @param projectiles The list of projectiles to check.
     * @return true if the rat is hit by any projectile, false otherwise.
     */
    public boolean isHitByProjectile(ArrayList<Projectile> projectiles) {
        for (Projectile rect : projectiles) {
            if (rect.getRectangle().overlaps(spriteRect)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the projectile that hits the rat.
     * 
     * @param projectiles The list of projectiles to check.
     * @return The projectile that hits the rat, or null if no projectile hits the
     *         rat.
     */
    public Projectile getHitByProjectile(ArrayList<Projectile> projectiles) {
        for (Projectile projectile : projectiles) {
            if (projectile.getRectangle().overlaps(spriteRect)) {
                return projectile;
            }
        }
        return null;
    }

    /**
     * Returns the bounty of the rat.
     * 
     * @return The bounty of the rat.
     */
    public int getBounty() {
        return bounty;
    }

    /**
     * Returns the points of the rat.
     * 
     * @return The points of the rat.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Swaps the image of the rat to the given image.
     * 
     * @param image The new image of the rat.
     */
    public void swapImage(ImageSwapper image) {
        currentState = image;
    }

    /**
     * Returns the current texture of the rat.
     * 
     * @return The current texture of the rat.
     */
    public Texture getTexture() {
        return textures.get(currentState);
    }

    /**
     * Returns the rectangle representing the rat.
     * 
     * @return The rectangle representing the rat.
     */
    public Rectangle getRectangle() {
        return spriteRect;
    }

    // må kanskje endre denne, hvis et prosjektil treffer en rotte, så skal den ta
    // skade. Er det berde.
    /**
     * Reduces the health of the rat by the given damage.
     * 
     * @param damage The amount of damage to inflict on the rat.
     */
    public void takeDamage(int damage) {
        health -= damage;
    }

    /**
     * Enum representing the possible directions of the rat.
     */
    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        OUT;
    }

    /**
     * Adds time to the rat's internal timer.
     */
    public void addTime() {
        this.secs += 0.05;

    }

    /**
     * Returns the current direction of the rat.
     * 
     * @return The current direction of the rat.
     */
    public Direction getDirection() {
        int category;
        if (secs < 4)
            category = 1;
        else if (secs < 9)
            category = 2;
        else if (secs < 14)
            category = 3;
        else if (secs < 24)
            category = 4;
        else if (secs < 31)
            category = 5;
        else if (secs < 35)
            category = 6;
        else if (secs < 51)
            category = 7;
        else if (secs < 57)
            category = 8;
        else if (secs < 62)
            category = 9;
        else if (secs < 67)
            category = 10;
        else if (secs < 72.5)
            category = 11;
        else if (secs < 78)
            category = 12;
        else if (secs < 85)
            category = 13;
        else if (secs < 87)
            category = 14;
        else
            return Direction.OUT;

        switch (category) {
            case 1:
            case 3:
            case 7:
            case 11:
                return Direction.RIGHT;
            case 2:
            case 8:
            case 10:
            case 12:
            case 14:
                return Direction.UP;
            case 4:
            case 6:
                return Direction.DOWN;
            case 5:
            case 9:
            case 13:
                return Direction.LEFT;
            default:
                throw new AssertionError("Unexpected value: " + category);
        }
    }

    //Skal vi ha med dette egentlig, ser bedre når ny rotte?
    private float getRotationAngle() {
        Direction dir = getDirection();
        switch (dir) {
            case UP:
                return 0;
            case DOWN:
                return 180;
            case LEFT:
                return 90;
            case RIGHT:
                return -90;
            case OUT:
                // Assuming no rotation for OUT direction.
                return 0;
            default:
                throw new Error("Unexpected Direction: " + dir);
        }
    }


    /**
     * Rotates the rat's image to face its direction.
     */
    public void rotateImage() {
        float angle = getRotationAngle();
        this.sprite.setOriginCenter();
        this.sprite.setRotation(angle);
    }
    

    @Override
    public void move() {
        Direction dir = getDirection(); 
        switch (dir) {
            case UP:
                pos.y += speed;
                break;
            case DOWN:
                pos.y -= speed;
                break;
            case RIGHT:
                pos.x += speed;
                break;
            case LEFT:
                pos.x -= speed;
                break;
            default:
                break;
        }
    
        this.sprite.setPosition(pos.x, pos.y);
        rotateImage(); 
    }

    public Sprite getSprite() {
        return sprite;
    }
    

    @Override
    public void render(SpriteBatch batch) {
    }


    @Override
    public void killedAnimation() {
        swapImage(ImageSwapper.DEAD);
        health = 0;
        speed = 0;
    }

    public void updateCoinVisibility(float deltaTime) {
        if (isKilled()) {
            coinVisibleTime += deltaTime;
        }
    }

    @Override
    public boolean isKilled() {
        return health <= 0;
    }

    /**
     * Checks if the rat is out.
     * 
     * @return true if the rat is out, false otherwise.
     */
    public boolean isOut() {
        if (this.getDirection() == Direction.OUT) {
            return true;
        }
        return false;
    }

    /**
     * Sets the position of the rat.
     * 
     * @param pos The new position of the rat.
     */
    public void setPosition(Vector2 pos) {
        this.pos = pos;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Vector2 getPosition() {
        return pos;
    }

    /**
     * Freezes the rat.
     */
    public void freeze() {
        isFrozen = true;
        // speed = speed / 2;
        swapImage(ImageSwapper.FROZEN);
    }

    /**
     * Unfreezes the rat.
     */
    public void unfreeze() {
        isFrozen = false;
        // speed = speed * 2;
        swapImage(ImageSwapper.ALIVE);
    }

    /**
     * Checks if the rat is frozen.
     * 
     * @return true if the rat is frozen, false otherwise.
     */
    public boolean isFrozen() {
        return isFrozen;
    }
}
