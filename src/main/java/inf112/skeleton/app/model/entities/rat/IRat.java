package inf112.skeleton.app.model.entities.rat;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.model.entities.rat.Rat.Direction;


public interface IRat {

   
    /**
     * @return true if the rat has less than 1 health
     */
    boolean isKilled();

   
    /**
     * @return the health of the rat
     */
    int getHealth();


    /**
     * @return the position of the rat
     */
    Vector2 getPosition();

    
    /**
     * @return true if the reward has been claimed
     */
    boolean isrewardClaimed();

  
    /**
     * @return true if the rat has exited the board, method is made so that the rat can be removed from the board and does only take 1 life
     */
    boolean isExited();


    /**
     * @return sets the rewardClaimed to true
     */
    void rewardClaimed();

 
    /**
     * @return sets the exited to true
     */
    void exit();
 

    /**
     * @return the bounty of the rat
     */
    int getBounty();

  
    /**
     * @return the points of the rat
     */
    int getPoints();

   
    /**
     * @return the texture of the rat
     */
    Texture getTexture();

    /**
     * @return the rectangle of the rat
     */
    Rectangle getRectangle();

    /**
     * @param damage the damage the rat should take
     * rat takes damage, if health is less than 1, the rat is killed and killedAnimation is called
     */
    void takeDamage(int damage);

   
    /**
     * @return if the Direction of the rat is out or not
     */
    boolean isOut();

  
    /**
     * @param pos the position the rat should be set to
     * sets the position of the rat
     */
    void setPosition(Vector2 pos);


    /**
     * lowers the speed, swaps the image of the rat to a frozen texture
     */
    void freeze();

    /**
     * sets the speed back to normal, swaps the image of the rat to the alive texture
     */
    void unfreeze();

    /**
     * @param deltaTime 
     * if the rat is killed the coinVisibleTime is increased by deltaTime
     */
    void updateCoinVisibility(float deltaTime); 


    /**
     * creats the path a rat should follow
     */
    void createPath();


    /**
     * @param delta 
     * moves the rat along the path and rotates the rat based on the direction it is moving
     */
    void moveAlongPath(float delta);


    /**
     * @param controlPoint the controlPoint the rat should be set to
     * sets the controlPoint of the rat
     */
    void setControlPoint(int controlPoint);

    
    /**
     * @return the original speed of the rat
     */
    float getOriginalSpeed();

    
    /**
     * @return the effective speed of the rat
     */
    float getEffectiveSpeed();


    /**
     * @return controlPoints of the rat
     */
    Vector2[] getControlPoints();


    /**
     * @return int This returns the current control point index for the path.
     */
    int getCurrentControlPoint();


    /**
     * @return the path the rat should follow
     */
    CatmullRomSpline<Vector2> getPath();

    /**
     * @param newSpeed the new speed the rat should be set to
     * sets the effective speed of the rat
     */
    void setEffectiveSpeed(float newSpeed);

    /**
     * @return the direction the rat is moving
     */
    Direction getDirection();


    /**
     * @param dir the direction the rat should be set to
     * sets the direction of the rat
     */
    void setDirection(Direction dir);

    /**
     * @return the sprite of the rat
     */
    Sprite getSprite();

    /**
     * @return the time the coin should be visible
     */
    float getCoinVisibleTime();
   

    /**
     * @return if a rat is frozen or not
     */
    boolean isFrozen();

    /**
     * sets is frozen to true
     */
    void setFrozen(); 

    /**
     * 
     * @param counted sets the counted to true or false
     * sets the counted to true or false
     */
    void setCounted(boolean counted);

    /**
     *
     * @return if the rat is counted or not
     */
    boolean isCounted();
}


