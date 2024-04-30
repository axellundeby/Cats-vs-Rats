package inf112.skeleton.app.model.entities.cat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.view.GameResourceFactory;

@ExtendWith(MockitoExtension.class)
public class ShotgunCatTest {
    private ShotgunCat shotgunCat;
    @Mock
    private Texture aliveTextureMock;
    @Mock
    private Texture frozenTextureMock;
    @Mock
    private Texture deadTextureMock;
    @Mock
    private Sound mockSound; 

    private LinkedList<Rat> rats;

    @BeforeEach
    void setup() {
        GameResourceFactory mockFactory = mock(GameResourceFactory.class);
        lenient().when(mockFactory.getSound("sound/gunshot.mp3")).thenReturn(this.mockSound);
        lenient().when(mockFactory.getTexture(anyString())).thenReturn(mock(Texture.class));


        shotgunCat = new ShotgunCat(mockFactory);
            rats = new LinkedList<>();
            for (int i = 0; i < 3; i++) {
                rats.add(new Rat(100, 10, aliveTextureMock, 50, 20, frozenTextureMock, 25, deadTextureMock));
            }
    }

    @Test
    void testAttack() {
        if (shotgunCat.canAttack()) {
            int initialHealth = rats.getFirst().getHealth();
            shotgunCat.attack(rats);
            assertEquals(initialHealth - shotgunCat.getStrength(), rats.getFirst().getHealth());
        }
    }

    @Test
    void upgradeDamageTest() {
        int initialStrength = shotgunCat.getStrength();
        shotgunCat.upgradeDamage();
        assertEquals((int)(initialStrength * 1.25), shotgunCat.getStrength());
    }

    @Test
    void testUpgradeRange() {
        int initialRange = shotgunCat.getRange();
        shotgunCat.upgradeRange();
        assertEquals((int)(initialRange * 1.25), shotgunCat.getRange());
    }

    @Test
    void testUpgradeFireRate() {
        float initialFireRate = shotgunCat.getFireRate();
        shotgunCat.upgradeFireRate();
        assertEquals(initialFireRate * 0.75, shotgunCat.getFireRate(), 0.01);
    }

     @Test
    void testPlayAttackSound() {
        if (shotgunCat.canAttack()) {
            shotgunCat.attack(rats);
            verify(mockSound, times(1)).play(0.6f);
        }
    }

    
}
