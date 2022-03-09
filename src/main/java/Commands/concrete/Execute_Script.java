package Commands.concrete;

import Collection.WorkersCollection;
import Commands.Command;
import Exceptions.InvalidInputFormat;
import Parser.CommandsReader;

import java.io.IOException;
import java.util.ArrayList;

public class Execute_Script extends Command {
    private static ArrayList<String> paths = new ArrayList<>();
    public Execute_Script() {
        super("execute_script","read and execute a script from the file");
    }

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
        } catch (IOException | InvalidInputFormat e) {
            System.out.println(e.getMessage());
        }
    }
}
