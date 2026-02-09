package it.unibo.core;

import java.util.Optional;

import it.unibo.api.enigmas.Enigma;
import it.unibo.api.rooms.RoomManager;
import it.unibo.input.Command;
import it.unibo.input.Controller;
import it.unibo.input.StopMovement;
import it.unibo.view.View;

/**
 * simple game engine containing the main loop
 */
public class GameEngine implements Controller {

    private static final double NANOS_IN_A_SECOND = 1_000_000_000.0;
    private static final double NANOS_IN_A_MILLISECOND = 1_000_000.0;

    private Command currentCommand;
    private View view;
    private RoomManager model;

    /**
     * basic constructor
     */
    public GameEngine(View view, RoomManager model) {
        this.currentCommand = new StopMovement();
        this.view = view;
        this.model = model;
     }

    /**
     * main game loop
     * automatically updates the {@code Time.deltaTime}
     */
    public void run() {
        long previousCycleStartTime = System.nanoTime();

        //TO DO: once the game state will be implemented, the cycle should finish if "game over"
        while(true) {
            long currentCycleStartTime = System.nanoTime();
            double deltaTime = (currentCycleStartTime - previousCycleStartTime) / NANOS_IN_A_SECOND;
            Time.updateDeltaTime(deltaTime);

            this.processInput();
            this.update();
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
        final long frameDuration = System.nanoTime() - currentCycleStartTime;
        final long sleepTime = this.calculateCapFrameTime() - frameDuration;

        if(sleepTime > 0) {
            try {
                Thread.sleep((int) (sleepTime / NANOS_IN_A_MILLISECOND), (int) (sleepTime % NANOS_IN_A_MILLISECOND));
            } catch(final Exception exep) {}
        }
    }

    /**
     * calculates the period for fps cap
     * @return the correct time between frames to not overcome the fps cap
     */
    private long calculateCapFrameTime() {
        return (long) (NANOS_IN_A_SECOND / GameSettings.FPS_CAP.getValueAsInteger());
    }

    /**
     * updates the game state
     * @param elapsed time elapsed between previous and current frame
     */
    private void update() {
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
        if (currentCommand != null){
			Optional<Enigma> enigma = currentCommand.execute(model);
            view.updateView(model.getCurrentRoom(), model.getCurrentPosition(), enigma);
		}  
    }

    @Override
    public void catchCommand(Command cmd) {
        this.currentCommand = cmd;
    }
}
