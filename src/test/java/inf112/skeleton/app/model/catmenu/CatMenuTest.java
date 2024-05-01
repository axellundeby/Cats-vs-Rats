package inf112.skeleton.app.model.catmenu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.entities.cat.AttackCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.view.GameResourceFactory;
import inf112.skeleton.app.view.TimeSource;

public class CatMenuTest {
    private static Texture mockTexture;
    private static TimeSource mockTimeSource;
    private static GameResourceFactory mockFactory;
    private static ApplicationListener listener;
    private static SpriteBatch spriteBatchMock;
    private static ShapeRenderer shapeRendererMock;

    private CatMenu catMenu;


    @BeforeAll
    static void setUpBeforeAll() {

        mockFactory = mock(GameResourceFactory.class);
        mockTexture = mock(Texture.class);
        mockTimeSource = mock(TimeSource.class);

        spriteBatchMock = mock(SpriteBatch.class);
        shapeRendererMock = mock(ShapeRenderer.class);


        model = new SkadedyrModel(mockFactory, mockTimeSource);

        

        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        listener = new ApplicationListener() {

            @Override
            public void create() {
            }

            @Override
            public void resize(int width, int height) {
            }

            @Override
            public void render() {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
            }
        };
        new HeadlessApplication(listener, config);


    }

    @BeforeEach
    void beforeEach(){
        catMenu = new CatMenu(mockFactory, mockTimeSource);
    }

    @Test
    public void constructionTest(){
        CatMenu localCatMenu = new CatMenu(mockFactory, mockTimeSource);
        assertNotNull(localCatMenu);
    }

    @Test
    public void initTest(){
        assertThrows(NullPointerException.class, () -> catMenu.init());
    }

    @Test
    public void selectionTest1(){
        assertNull(catMenu.getSelectedCat());
        // Try to select a cat not there
        catMenu.selector(new Vector2());
        assertNull(catMenu.getSelectedCat());
        
    }

    @Test
    public void selectionTest2(){
        assertNull(catMenu.getSelectedCat());
        Cat dummyCat = new AttackCat(0, 0, Arrays.asList(mockTexture), Arrays.asList(mockTexture), 0, 0);
        catMenu.addCat(dummyCat);
        catMenu.selector(new Vector2(5, 5));
        assertNotNull(catMenu.getSelectedCat());
    }

    @Test
    public void selectionTest3(){
        assertNull(catMenu.getSelectedCat());
        Cat dummyCat = new AttackCat(0, 0, Arrays.asList(mockTexture), Arrays.asList(mockTexture), 0, 0);
        catMenu.addCat(dummyCat);
        catMenu.selector(new Vector2(5, 5));
        assertEquals(dummyCat, catMenu.getSelectedCat());
    }

    @Test
    public void deselectionTest(){
        assertNull(catMenu.getSelectedCat());
        Cat dummyCat = new AttackCat(0, 0, Arrays.asList(mockTexture), Arrays.asList(mockTexture), 0, 0);
        catMenu.addCat(dummyCat);
        catMenu.selector(new Vector2(5, 5));
        assertNotNull(catMenu.getSelectedCat());
        
        catMenu.deselect();
        assertNull(catMenu.getSelectedCat());
    }

    @Test
    public void rectangleTest1(){
        assertEquals(0, catMenu.getX());
    }

    @Test
    public void rectangleTest2(){
        assertEquals(0, catMenu.getY());
    }

    @Test
    public void drawTests(){
        Cat dummyCat = new AttackCat(0, 0, Arrays.asList(mockTexture), Arrays.asList(mockTexture), 0, 1);
        catMenu.addCat(dummyCat);
        catMenu.selector(new Vector2(5, 5));

        catMenu.draw(shapeRendererMock);
        catMenu.draw(spriteBatchMock, 0);
    }
}

