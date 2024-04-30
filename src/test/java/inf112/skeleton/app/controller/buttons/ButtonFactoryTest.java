// package inf112.skeleton.app.controller.buttons;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import com.badlogic.gdx.graphics.Texture;

// import inf112.skeleton.app.model.SkadedyrModel;
// import inf112.skeleton.app.view.GameResourceFactory;
// import inf112.skeleton.app.view.TimeSource;

// public class ButtonFactoryTest {

//     @Mock
//     private GameResourceFactory mockResourceFactory;
//     @Mock
//     private TimeSource mockTimeSource;
//     @Mock
//     private Texture mockTextureUp;
//     @Mock
//     private Texture mockTextureDown;

//     private ButtonFactory buttonFactory;
//     private SkadedyrModel mockModel;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         when(mockResourceFactory.getTexture("up")).thenReturn(mockTextureUp);
//         when(mockResourceFactory.getTexture("down")).thenReturn(mockTextureDown);

//         mockModel = mock(SkadedyrModel.class);

//         // Create an instance of ButtonFactory with the mocked model
//         buttonFactory = new ButtonFactory(mockModel);
//     }

//     @Test
//     void testCreateMenuButton() {
//         // Test to ensure that the created menu button is not null
//         var menuButton = buttonFactory.createMenuButton(mockTextureUp, mockTextureDown, 100, 100, () -> System.out.println("Menu Button Pressed"));
//         assertNotNull(menuButton, "The created menu button should not be null.");
//     }

//     @Test
//     void testCreateUpgradeButton() {
//         // Test to ensure that the created upgrade button is not null
//         var upgradeButton = buttonFactory.createUpgradeButton(100, mockTextureUp, mockTextureDown, 150, 150, cat -> cat.upgradeDamage());
//         assertNotNull(upgradeButton, "The created upgrade button should not be null.");
//     }

//     @Test
//     void testCreateImageButton() {
//         // Directly testing the internal method for educational purposes
//         var imageButton = ButtonFactory.createImageButton(mockTextureUp, mockTextureDown);
//         assertNotNull(imageButton, "The created image button should not be null.");
//     }
// }
