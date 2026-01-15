package it.unibo.core;

/**
 * simple game engine containing the main loop
 */
public class GameEngine {

    /**
     * basic constructor
     */
    public GameEngine() { }

    /**
     * main game loop
     */
    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();

        //TO DO: once the game state will be implemented, the cycle should finish if "game over"
        while(true) {
            long currentCycleStartTime = System.currentTimeMillis();

            this.processInput();
            this.update(currentCycleStartTime - previousCycleStartTime);
            this.render();

            this.waitUntilNextFrame(currentCycleStartTime);

            previousCycleStartTime = currentCycleStartTime;
        }
    }

    /**
     * waits for the next frame, to not exceed the fps cap
     * @param currentCycleStartTime the current game loop start time
     */
    private void waitUntilNextFrame(final long currentCycleStartTime) {
        long deltaTime = System.currentTimeMillis() - currentCycleStartTime;

        if(deltaTime <= GameSettings.FPS_CAP.getValueAsInteger()) {
            try {
                Thread.sleep(this.calculateCapFrameTime() - deltaTime);
            } catch(final Exception exep) {}
        }
    }

    /**
     * calculates the period for fps cap
     * @return fps cap frame time
     */
    private int calculateCapFrameTime() {
        return (1_000 / GameSettings.FPS_CAP.getValueAsInteger());
    }

    /**
     * updates the game state
     * @param elapsed time elapsed between previous and current frame
     */
    private void update(final long elapsed) {
        //TO DO
        throw new IllegalStateException("method not implemented yet");
    }

    /**
     * tells the view to render the current scene
     */
    private void render() {
        //TO DO
        throw new IllegalStateException("method not implemented yet");
    }

    /**
     * processes the movement based on the input and the deltaTime
     */
    private void processInput() {
        //TO DO
        throw new IllegalStateException("method not implemented yet");   
    }
}
