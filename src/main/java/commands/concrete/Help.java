package commands.concrete;

import commands.Command;

/**
 * Help command. Displays help on available commands.
 */
public class Help extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Help() {
        super("help", "display help on available commands");
    }

    /**
     * Displays all commands and their descriptions.
     *
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public void action(String args) {
        System.out.println("The list of available commands:\n"
        + "- add                           || add a new element to the collection\n"
        + "- average_of_salary             || get the average value of the salary\nfor all items in the collection\n"
        + "- clear                         || remove all workers from collection\n"
        + "- execute_script {file name}    || read and execute a script from the file\n"
        + "- exit                          || terminate program (without saving to file)\n"
        + "- filter_by_position {position} || display elements whose position field value is equal to the given one\n"
        + "- group_counting_by_coordinates || group the elements of the collection by the value of the field coordinates," +
                " display the number of elements in each group\n"
        + "- help                          || display help on available commands\n"
        + "- info                          || print information about the collection to standard output\n"
        + "- insert_at {index}             || add a new worker to a given position\n"
        + "- remove_at {index}             || remove an element at a give position\n"
        + "- remove_by_id {id}             || remove worker by id\n"
        + "- remove_lower                  || remove from the collection all elements lower than the given one\n"
        + "- save                          || save the collection to a file\n"
        + "- show                          || print all elements of collection to standard output\n"
        + "- update {id}                   || update the value of the collection element whose id is equal to the given one\n");
    }
}
