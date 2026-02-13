package it.unibo.core;

import java.util.List;
import java.util.Optional;

import it.unibo.api.Position;
import it.unibo.api.Vector2D;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.rooms.Room;
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
    private List<Room> rooms;

    /**
     * basic constructor
     * @param view the view
     * @param model the model
     * @param rooms the list of the rooms
     */
    public GameEngine(View view, RoomManager model, List<Room> rooms) {
        this.currentCommand = new StopMovement();
        this.view = view;
        this.model = model;
        this.rooms = rooms;
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

            Vector2D v2d = this.processInput();
            Optional<Enigma> enigma = this.update(Time.deltaTime(), v2d);
            this.render(enigma);

            this.waitUntilNextFrame(currentCycleStartTime);

            if (enigma.isPresent()) {
                previousCycleStartTime = System.nanoTime(); 
            } else {
                previousCycleStartTime = currentCycleStartTime;
            }
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
    private Optional<Enigma> update(double elapsed, Vector2D v2d) {
        Position currentPosition = model.getCurrentPosition();
        Position nextPosition = currentPosition.sum(v2d.mul(1*elapsed));
        int xFloor = (int) Math.floor(nextPosition.getX());
        int yFloor = (int) Math.floor(nextPosition.getY());
        int xCeil = (int) Math.ceil(nextPosition.getX());
        int yCeil = (int) Math.ceil(nextPosition.getY());

        Position roundDownNextPosition = new Position(xFloor, yFloor);
        Boolean collidingDown = model.isPlayerColliding(roundDownNextPosition);
        Position roundUpNextPosition = new Position(xCeil, yCeil);
        Boolean collidingUp = model.isPlayerColliding(roundUpNextPosition);
        Position roundDownUpNextPosition = new Position(xFloor, yCeil);
        Boolean collidingDownUp = model.isPlayerColliding(roundDownUpNextPosition);
        Position roundUpDownNextPosition = new Position(xCeil, yFloor);
        Boolean collidingUpDown = model.isPlayerColliding(roundUpDownNextPosition);

        if(collidingDown || collidingUp || collidingDownUp || collidingUpDown) {
            boolean eventDown = model.isEnteringAnEvent(roundDownNextPosition);
            boolean eventUp = model.isEnteringAnEvent(roundUpNextPosition);
            boolean eventDownUp = model.isEnteringAnEvent(roundDownUpNextPosition);
            boolean eventUpDown = model.isEnteringAnEvent(roundUpDownNextPosition);
            if(eventDown) {
                Room vecchiaStanza = model.getCurrentRoom();
                model.enterDoor(roundDownNextPosition, rooms);
                // Controllo l'enigma solo se la stanza NON è cambiata
                if(model.getCurrentRoom() == vecchiaStanza) {
                    return model.enterEnigma(roundDownNextPosition);
                }
                return Optional.empty(); // Stanza cambiata con successo, esci!
                
            } else if (eventUp) {
                Room vecchiaStanza = model.getCurrentRoom();
                model.enterDoor(roundUpNextPosition, rooms);
                if(model.getCurrentRoom() == vecchiaStanza) {
                    return model.enterEnigma(roundUpNextPosition);
                }
                return Optional.empty();
                
            } else if (eventDownUp) {
                Room vecchiaStanza = model.getCurrentRoom();
                model.enterDoor(roundDownUpNextPosition, rooms);
                if(model.getCurrentRoom() == vecchiaStanza) {
                    return model.enterEnigma(roundDownUpNextPosition);
                }
                return Optional.empty();
                
            } else if (eventUpDown) {
                Room vecchiaStanza = model.getCurrentRoom();
                model.enterDoor(roundUpDownNextPosition, rooms);
                if(model.getCurrentRoom() == vecchiaStanza) {
                    return model.enterEnigma(roundUpDownNextPosition);
                }
                return Optional.empty();
            }
            if(collidingDown == true) {
                model.computeMove(true, roundUpNextPosition);
            }
            if(collidingUp == true) {
                model.computeMove(true, roundDownNextPosition);
            }
            if(collidingDownUp == true) {
                model.computeMove(true, roundUpDownNextPosition);
            }
            if(collidingUpDown == true) {
                model.computeMove(true, roundDownUpNextPosition);
            }
        } else {
            model.computeMove(true, nextPosition);
        }
        return Optional.empty();
    }

    /**
     * tells the view to render the current scene
     */
    private void render(Optional<Enigma> enigma) {
        view.updateView(model.getCurrentRoom(), model.getCurrentPosition(), enigma);
    }

    /**
     * processes the movement based on the input and the deltaTime
     */
    private Vector2D processInput() {
        if (currentCommand != null){
			return currentCommand.execute();
		}  
        return new Vector2D(0, 0);
    }

    @Override
    public void catchCommand(Command cmd) {
        this.currentCommand = cmd;
    }
}
