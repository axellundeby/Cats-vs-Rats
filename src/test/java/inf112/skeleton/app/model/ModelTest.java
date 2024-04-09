package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;


import inf112.skeleton.app.main.SkadedyrGame;
import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.rat.BasicRat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class ModelTest {
    static SkadedyrModel model;
    @BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationListener() {

            @Override
			public void create() {
                // TODO Auto-generated method stub
				
			}

			@Override
			public void resize(int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void render() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void pause() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resume() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}};
        new HeadlessApplication(listener, config);

        }
    

        @BeforeEach
        void beforeEach() {
            // Initialize your model here if necessary
            model = new SkadedyrModel();
            
            // Mocking the Gdx static class if necessary
            // Mockito cannot directly mock static methods without the Mockito-inline extension,
            // but you can use interfaces or wrapper classes as a workaround.
        }
        @Test
        public void testAddCat(){
            Cat cat = mock(BasicCat.class);
            assertTrue(model.getCats().isEmpty());
            model.addCat(cat);
            assertTrue(model.getCats().size() == 1);

        }
        @Test
        public void attackQueueTest1(){
            // Mock creation
            Cat cat1 = mock(BasicCat.class);
            Cat cat2 = mock(FreezeCat.class);
            
            // Define mock behavior
            when(cat1.getPosition()).thenReturn(new Vector2(50, 0));
            when(cat2.getPosition()).thenReturn(new Vector2(60, 0));
            
            model.addCat(cat1);
            model.addCat(cat2);
            
            Rat rat1 = mock(BasicRat.class); 
            Rat rat2 = mock(BasicRat.class); 

            model.addRat(rat1);
            model.addRat(rat2);

            rat1.setPosition(new Vector2(45, 0));
            rat2.setPosition(new Vector2(58, 0));
            
            HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();
            for (Cat cat : model.getCats()) {

            }
            // Assertions and verifications
            // Verify the interaction or the state after performing actions
            // For example, verify if a method was called on the mock
            // verify(cat1).setPosition(any(Vector2.class));
            // Assert conditions based on your logic
        }
        

    // @Test
    public void attackQueueTest2(){
        //Cat cat1 = new BasicCat();
        Cat cat1 = new BasicCat();
        cat1.setPos(50, 0);
        Cat cat2 = new FreezeCat();
        cat2.setPos(60, 0);
        model.addCat(cat1);
        model.addCat(cat2);
        Rat rat1 = new BasicRat();
        Rat rat2 = new BasicRat();
        // rat 1 is closer to cat 1 and rat 2 is closer to cat 2
        rat1.setPosition(new Vector2(45, 0));
        rat2.setPosition(new Vector2(58, 0));
        HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();
        for (Cat cat : model.getCats()) {
            System.out.println(cat.toString());
        }
    }
}
