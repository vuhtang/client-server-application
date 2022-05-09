package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import commands.CommandExecutor;
import transferring.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Help command. Displays help on available commands.
 */
public class Help extends Command {
    private final CommandExecutor executor;
    /**
     * Initialised the name and the description of the new command.
     */
    public Help(CommandExecutor executor) {
        super("help", "display help on available commands");
        this.executor = executor;
    }

    /**
     * Displays all commands and their descriptions.
     *
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        //List<String> response = new ArrayList<>();
        return executor.getCommandsDescription();
//        response.add("""
//                The list of available commands:
//                - register                      || register new user
//                - log_in                        || application log in
//                - add                           || add a new element to the collection
//                - average_of_salary             || get the average value of the salary for all items in the collection
//                - clear                         || remove all workers from collection
//                - execute_script {file name}    || read and execute a script from the file
//                - exit                          || terminate program (without saving to file)
//                - filter_by_position {position} || display elements whose position field value is equal to the given one
//                - group_counting_by_coordinates || group the elements of the collection by the value of the field coordinates, display the number of elements in each group
//                - help                          || display help on available commands
//                - info                          || print information about the collection to standard output
//                - insert_at {index}             || add a new worker to a given position
//                - remove_at {index}             || remove an element at a give position
//                - remove_by_id {id}             || remove worker by id
//                - remove_lower                  || remove from the collection all elements lower than the given one
//                - show                          || print all elements of collection to standard output
//                - update {id}                   || update the value of the collection element whose id is equal to the given one""");
//        return response;
    }
}
