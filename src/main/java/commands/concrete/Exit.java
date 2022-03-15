package commands.concrete;

import collection.WorkersCollection;
import commands.Command;

/**
 * Exit command. In fact, it is just a label for terminating the command execution loop.
 */
public class Exit extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Exit() {
        super("exit", "terminate program (without saving to file)");
    }

    /**
     * In fact, this method will never be used :)
     *
     * @param workers the collection of workers as an imperfection of the program model
     * @param args    an empty string as an imperfection of the program model
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        System.out.println("Application has completed its work");
    }
}
