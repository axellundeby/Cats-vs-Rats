package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.EnumMap;

public class Rat implements IRat {
    private float speed;
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
    private boolean isFrozen = false;
    public ImageSwapper currentState = ImageSwapper.ALIVE;
    private EnumMap<ImageSwapper, Texture> textures = new EnumMap<>(ImageSwapper.class);
    int halfsize = 25;
    private CatmullRomSpline<Vector2> path;
    private float progress;
    private Vector2[] controlPoints;
    private Direction direction = Direction.RIGHT;
    public int currentControlPoint = 0; 
    private float freezeTimer = 0;
    private static final float RAT_FREEZE_DELAY = 20;
    private float originalSpeed;
    private float effectiveSpeed;

    

    public Rat(int health, float speed, Texture texture, Integer bounty, Integer points, Texture frozenTexture, int halfsize, Texture deadTexture) {
        this.health = health;
        this.speed = speed;
        this.points = points;
        this.bounty = bounty;
        createPath();
        this.pos = new Vector2(controlPoints[0]);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(halfsize * 2, halfsize * 2);
        this.sprite.setPosition(pos.x - halfsize, pos.y - halfsize);
        this.secs = 0;
        textures.put(ImageSwapper.ALIVE, texture);
        textures.put(ImageSwapper.FROZEN, frozenTexture);
        textures.put(ImageSwapper.DEAD, deadTexture);
        this.spriteRect = new Rectangle(pos.x - halfsize, pos.y - halfsize, halfsize * 2, halfsize * 2);
        this.originalSpeed = speed;
        this.effectiveSpeed = speed;

    }

    public void moveAlongPath(float delta) {
        if (currentControlPoint < controlPoints.length - 2) {
            Vector2 currentPoint = controlPoints[currentControlPoint];
            Vector2 nextPoint = controlPoints[currentControlPoint + 1];
            Vector2 directionToNextPoint = new Vector2(nextPoint).sub(currentPoint).nor();
            Vector2 movementThisFrame = new Vector2(directionToNextPoint).scl(effectiveSpeed * delta);
            pos.add(movementThisFrame);
            if (currentPoint.dst(pos) >= currentPoint.dst(nextPoint)) {
                pos.set(nextPoint);
                currentControlPoint++;
            }
            sprite.setPosition(pos.x - halfsize, pos.y - halfsize); 
            spriteRect.setPosition(pos.x - halfsize, pos.y - halfsize); 
            if (pos.epsilonEquals(controlPoints[currentControlPoint], 1.0f)) {
                updateDirection(controlPoints[currentControlPoint], controlPoints[currentControlPoint + 1]);
                rotateImage();
            }
        }
    }

    public int getCurrentControlPoint() {
        return currentControlPoint;
    }

    public void setEffectiveSpeed(float newSpeed) {
        this.effectiveSpeed = newSpeed;
    }

    public void createPath() {
        controlPoints = new Vector2[] {
            new Vector2(-10,290),
            new Vector2(8,290),
            new Vector2(200,290),
            new Vector2(200,422),
            new Vector2(85,422),
            new Vector2(85,616),
            new Vector2(106,616),
            new Vector2(435,620),
            new Vector2(435,290),
            new Vector2(654,290),
            new Vector2(654,360),
            new Vector2(875,360),
            new Vector2(875,490),
            new Vector2(660,490),
            new Vector2(660,610),
            new Vector2(1080,610),
            new Vector2(1080,310),
            new Vector2(1200,310),
            new Vector2(1500,310),
           
            
        };
        this.path = new CatmullRomSpline<>(controlPoints, false);
    }

    public Vector2[] getControlPoints() {
        return controlPoints;
    }

    public CatmullRomSpline<Vector2> getPath() {
        return path;
    }


    private void updateDirection(Vector2 current, Vector2 next) {
        if(current.x > 1150){ 
            direction = Direction.OUT;
        }
        else if (next.x > current.x) {
            direction = Direction.RIGHT;
        } else if (next.x < current.x) {
            direction = Direction.LEFT;
        } else if (next.y > current.y) {
            direction = Direction.UP;
        } else if (next.y < current.y) {
            direction = Direction.DOWN;
        }
    }

    public Direction getDirection(){
        return direction;
    }

    @Override
    public boolean isrewardClaimed() {
        return rewardClaimed;
    }

    @Override
    public boolean isExited() {
        return exited;
    }

    @Override
    public void rewardClaimed() {
        this.rewardClaimed = true;
    }

    @Override
    public void exit() {
        this.exited = true;
    }

    private enum ImageSwapper {
        ALIVE,
        FROZEN,
        DEAD;
    }

   

    @Override
    public int getBounty() {
        return bounty;
    }

    @Override
    public int getPoints() {
        return points;
    }

    private void swapImage(ImageSwapper image) {
        currentState = image;
    }

    @Override
    public Texture getTexture() {
        return textures.get(currentState);
    }

    @Override
    public Rectangle getRectangle() {
        return spriteRect;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0; 
            killedAnimation(); 
        }
    }

    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        OUT;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

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
                return 0;
            default:
                throw new Error("Unexpected Direction: " + dir);
        }
    }

    @Override
    public void rotateImage() {
        float angle = getRotationAngle();
        this.sprite.setOriginCenter();
        this.sprite.setRotation(angle);
    }

   
    public Sprite getSprite() {
        return sprite;
    }

    public void killedAnimation() {
        swapImage(ImageSwapper.DEAD);
        health = 0;
        speed = 0;
        this.sprite.setTexture(getTexture());
    }

    public void updateCoinVisibility(float deltaTime) {
        if (isKilled()) {
            coinVisibleTime += deltaTime;
        }
    }

    public float getCoinVisibleTime() {
        return coinVisibleTime;
    }

    @Override
    public boolean isKilled() {
        return health <= 0;
    }

    @Override
    public boolean isOut() {
        if (this.getDirection() == Direction.OUT || this.pos.x >= 1150) {
            return true;
        }
        return false;
    }

    @Override
    public void setPosition(Vector2 pos) {
        this.pos = pos;
        rectangleUpdater();
    }

    private void rectangleUpdater(){
        spriteRect.x = pos.x - halfsize;
        spriteRect.y = pos.y - halfsize;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Vector2 getPosition() {
        return pos;
    }

    @Override
    public void freeze(float deltaTime) {
        if (!isFrozen) {
            effectiveSpeed = originalSpeed - 15;  
            swapImage(ImageSwapper.FROZEN);
            this.sprite.setTexture(getTexture());
            isFrozen = true;
        }
        freezeTimer += deltaTime;
        if (freezeTimer > RAT_FREEZE_DELAY) {
            unfreeze(); 
        }
    }
    
    public void unfreeze() {
        isFrozen = false;
        effectiveSpeed = originalSpeed;
        swapImage(ImageSwapper.ALIVE);
        this.sprite.setTexture(getTexture());
        freezeTimer = 0;
    }
    

    public boolean isFrozen() {
        return isFrozen;
    }
    public void setFrozen() {
        isFrozen = true;
    }

    public float getEffectiveSpeed() {
       return effectiveSpeed;
    }
}
