package commands.concrete;

import commands.Command;
import exceptions.InvalidInputException;
import parser.CommandReader;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Execute script command. It executes script from a file in which
 * commands are written one per line. commands are executed sequentially.
 * If a cycle is detected, the execution will stop.
 */
public class ExecuteScript extends Command {
    private final Transfer transfer;
    /**
     * This container is implemented with ArrayList and checks for looping.
     * The cycle can only occur if this is another script in the list of commands
     * that has already been executed.
     */
    private static final ArrayList<String> paths = new ArrayList<>();

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public ExecuteScript(Transfer transfer) {
        super("execute_script", "read and execute a script from the file");
        this.transfer = transfer;
    }

    /**
     * Executes commands from the file, path to which is given as argument,
     * using CommandReader class. After checking the path to the file, it will be added
     * to the list of already executed scripts. After executing all the commands in the file,
     * the path to this file will be removed from the list and the script will become executable again.
     *
     * @param args the path to the file with commands
     * @see CommandReader
     */
    @Override
    public void action(String args) {
        CommandReader commandReader = new CommandReader(transfer);
        if (paths.contains(args)) {
            System.out.println("Execution stopped due to a possible loop");
            return;
        } else paths.add(args);
        try {
            commandReader.executeCommands(args);
            paths.remove(args);
        } catch (IOException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
