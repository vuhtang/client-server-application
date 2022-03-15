package commands.concrete;

import collection.WorkersCollection;
import commands.Command;
import exceptions.InvalidInputException;
import parser.CommandsReader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Execute script command. It executes script from a file in which
 * commands are written one per line. commands are executed sequentially.
 * If a cycle is detected, the execution will stop.
 */
public class Execute_Script extends Command {
    /**
     * This container is implemented with ArrayList and checks for looping.
     * The cycle can only occur if this is another script in the list of commands
     * that has already been executed.
     */
    private static ArrayList<String> paths = new ArrayList<>();

    /**
     * Initialised the name and the description of the new command.
     */
    public Execute_Script() {
        super("execute_script", "read and execute a script from the file");
    }

    /**
     * Executes commands from the file, path to which is given as argument,
     * using CommandReader class. After checking the path to the file, it will be added
     * to the list of already executed scripts. After executing all the commands in the file,
     * the path to this file will be removed from the list and the script will become executable again.
     * @param workers the collection to work with
     * @param args the path to the file with commands
     * @see CommandsReader
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        CommandsReader commandsReader = new CommandsReader(workers);
        if (paths.contains(args)) {
            System.out.println("Execution stopped due to a possible loop");
            return;
        } else paths.add(args);
        try {
            commandsReader.executeCommands(args);
            paths.remove(args);
        } catch (IOException | InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
