// package inf112.skeleton.app.model;

// import static org.mockito.Mockito.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Graphics;
// import com.badlogic.gdx.Input;

// public class HanHerTest {
//     private SkadedyrModel model;
//     private Graphics graphicsMock;
//     private Input inputMock;

//     @BeforeEach
//     void setUp() {
//         model = new SkadedyrModel();

//         // Mock the Gdx components
//         graphicsMock = mock(Graphics.class);
//         inputMock = mock(Input.class);
//         Gdx.graphics = graphicsMock;
//         Gdx.input = inputMock;

//         // Set up default behaviors
//         when(graphicsMock.getDeltaTime()).thenReturn(0.016f); // simulate 60 FPS
//     }

//     @Test
//     void testClockTickUpdatesAnimations() {
//         model.clockTick();
//         verify(graphicsMock).getDeltaTime();
//         // Add assertions or verifications for method calls
//     }

//     @Test
//     void testClockTickMovesRats() {
//         // Arrange
//         Rat ratMock = mock(Rat.class);
//         model.addRat(ratMock);
//         model.clockTick();
//         // Assert
//         verify(ratMock).moveAlongPath(anyFloat()); // Confirm moveAlongPath is called
//     }

//     @Test
//     void testClockTickHandlesUserInput() {
//         model.clockTick();
//         // Here you would check interactions like clicks, potentially setting up specific states with when() and then()
//     }

//     @Test
//     void testClockTickRemovesDeadRats() {
//         Rat ratMock = mock(Rat.class);
//         when(ratMock.isKilled()).thenReturn(true);
//         model.addRat(ratMock);
//         model.clockTick();
//         assertTrue(model.getRats().isEmpty()); // Assuming dead rats are removed
//     }

//     @Test
//     void testClockTickRoundHandling() {
//         // Setup the model to a specific state where a round should end
//         // Call clockTick and verify that round over procedures are triggered
//     }
// }
