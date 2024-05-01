package inf112.skeleton.app.model.entities.rat;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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
     * rotates the image of the rat
     */
    void rotateImage();

   
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
     * 
     */
    void freeze();



}
