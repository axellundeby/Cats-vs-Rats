package inf112.skeleton.app.view.States;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameStateManager {
    private State currentState;

    public void set(State newState) {
        if (currentState != null) {
            currentState.dispose();
        }
        currentState = newState;
    }

    public void update(float dt) {
        if (currentState != null) {
            currentState.update(dt);
        }
    }

    public void render(SpriteBatch sb) {
        if (currentState != null) {
            System.out.println("Rendering GameStateManager, state = " + currentState);
            currentState.render(sb);
        }
    }
    public void dispose() {
        if (currentState != null) {
            currentState.dispose();
        }
    }
}
// public class GameStateManager {

//     private Stack<State> states;

//     public GameStateManager() {
//         states = new Stack<State>();
//     }

//     public void push(State state) {
//         states.push(state);
//     }

//     public void pop() {
//         states.pop();
//     }

//     public void set(State state) {
//         states.pop();
//         states.push(state);
//     }

//     public void update(float dt) {
//         states.peek().update(dt);
//     }

//     public void render(SpriteBatch sb) {
//         states.peek().render(sb);
        
//     }

    
    

