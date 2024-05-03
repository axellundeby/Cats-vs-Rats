package inf112.skeleton.app.model.entities.rat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.EnumMap;

public class Rat implements IRat {
    private Vector2 pos;
    private int health;
    private Rectangle spriteRect;
    private Integer bounty;
    private Integer points;
    private boolean rewardClaimed = false;
    private boolean exited = false;
    private float coinVisibleTime = 0f;
    private Sprite sprite;
    private boolean isFrozen = false;
    private ImageSwapper currentState = ImageSwapper.ALIVE;
    private EnumMap<ImageSwapper, Texture> textures = new EnumMap<>(ImageSwapper.class);
    private int halfsize = 25;
    private CatmullRomSpline<Vector2> path;
    private Vector2[] controlPoints;
    private Direction direction = Direction.RIGHT;
    private int currentControlPoint = 0; 
    private float originalSpeed;
    private float effectiveSpeed;

    

    public Rat(int health, float speed, Texture texture, Integer bounty, Integer points, Texture frozenTexture, int halfsize, Texture deadTexture) {
        this.health = health;
        this.points = points;
        this.bounty = bounty;
        createPath();
        this.pos = new Vector2(controlPoints[0]);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(halfsize * 2, halfsize * 2);
        this.sprite.setPosition(pos.x - halfsize, pos.y - halfsize);
        textures.put(ImageSwapper.ALIVE, texture);
        textures.put(ImageSwapper.FROZEN, frozenTexture);
        textures.put(ImageSwapper.DEAD, deadTexture);
        this.spriteRect = new Rectangle(pos.x - halfsize, pos.y - halfsize, halfsize * 2, halfsize * 2);
        this.originalSpeed = speed;
        this.effectiveSpeed = speed;

    }

    @Override
    public Vector2[] getControlPoints() {
        return controlPoints;
    }

    @Override
    public CatmullRomSpline<Vector2> getPath() {
        return path;
    }

    @Override
    public int getCurrentControlPoint() {
        return currentControlPoint;
    }

    @Override
    public void setEffectiveSpeed(float newSpeed) {
        this.effectiveSpeed = newSpeed;
    }

    @Override
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
    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public float getCoinVisibleTime() {
        return coinVisibleTime;
    }

    @Override
    public boolean isKilled() {
        return health <= 0;
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
    public boolean isFrozen() {
        return isFrozen;
    }
    @Override
    public void setFrozen() {
        isFrozen = true;
    }

    @Override
    public float getEffectiveSpeed() {
       return effectiveSpeed;
    }
    @Override
    public float getOriginalSpeed() {
        return originalSpeed;
    }

    @Override
    public void setControlPoint(int controlPoint) {
        this.currentControlPoint = controlPoint;
    }

   @Override
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
                updateDirection(currentPoint, nextPoint);
            }

            if (currentControlPoint == controlPoints.length - 1) {
                updateDirection(currentPoint, nextPoint);
            }
            sprite.setPosition(pos.x - halfsize, pos.y - halfsize); 
            spriteRect.setPosition(pos.x - halfsize, pos.y - halfsize); 
            if (pos.epsilonEquals(controlPoints[currentControlPoint], 1.0f)) {
                updateDirection(controlPoints[currentControlPoint], controlPoints[currentControlPoint + 1]);
                rotateImage();
            }
        }
    }

    @Override
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
            new Vector2(1300,310),
            new Vector2(1500,310),
        };
        this.path = new CatmullRomSpline<>(controlPoints, false);
    }

  
    private void updateDirection(Vector2 current, Vector2 next) {
        if (currentControlPoint == controlPoints.length - 1) {
            direction = Direction.OUT;
        } else if (next.x > current.x) {
            direction = Direction.RIGHT;
        } else if (next.x < current.x) {
            direction = Direction.LEFT;
        } else if (next.y > current.y) {
            direction = Direction.UP;
        } else if (next.y < current.y) {
            direction = Direction.DOWN;
        }
    }
    

    private enum ImageSwapper {
        ALIVE,
        FROZEN,
        DEAD;
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
        OUT,
    }

    private int getRotationAngle() {
        Direction dir = getDirection();
        switch (dir) {
            case DOWN:
                return 180;
            case LEFT:
                return 90;
            case RIGHT:
                return -90;
            default:
                return 0;
        }

    }


    private void rotateImage() {
        int angle = getRotationAngle();
        this.sprite.setOriginCenter();
        this.sprite.setRotation(angle);
    }


    private void killedAnimation() {
        swapImage(ImageSwapper.DEAD);
        effectiveSpeed = 0;
        this.sprite.setTexture(getTexture());
    }

    @Override
    public void updateCoinVisibility(float deltaTime) {
        if (isKilled()) {
            coinVisibleTime += deltaTime;
        }
    }

    @Override
    public boolean isOut() {
        if (this.getDirection() == Direction.OUT) {
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
    public void freeze() {
        effectiveSpeed = originalSpeed - 15;  
        swapImage(ImageSwapper.FROZEN);
        this.sprite.setTexture(getTexture());
    }
    
    @Override
    public void unfreeze() {
        isFrozen = false;
        effectiveSpeed = originalSpeed;
        swapImage(ImageSwapper.ALIVE);
        this.sprite.setTexture(getTexture());
    }
    
}
