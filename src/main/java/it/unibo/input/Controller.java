package it.unibo.input;

/**
 * interface for command management
 */
public interface Controller {
    
    /**
    * handle command 
    * @param cmd command
    */
    void catchCommand(Command cmd);

}
