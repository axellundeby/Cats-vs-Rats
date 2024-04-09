package inf112.skeleton.app.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.model.entities.cat.BasicCat;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.cat.FreezeCat;
import inf112.skeleton.app.model.entities.rat.BasicRat;
import inf112.skeleton.app.model.entities.rat.Rat;

public class ModelTest {
    static SkadedyrModel model;
    @BeforeAll
	static void setUpBeforeAll() {
        model = new SkadedyrModel();
    }

    @BeforeEach
    void beforeEach(){}

    @Test
    public void attackQueueTest(){
        Cat cat1 = new BasicCat();
        Cat cat2 = new FreezeCat();
        model.addCat(cat1);
        model.addCat(cat2);
        Rat rat1 = new BasicRat();
        HashMap<Cat, LinkedList<Rat>> attackMap = model.attackQueueForEachCat();
    }
}
