package commands.concrete;

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
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public void action(String args) {
        System.out.println("Goodbye!!!!!!!");
        System.exit(0);
    }
}
