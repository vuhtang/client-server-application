package commands.concrete;

import collection.WorkersCollection;
import commands.Command;

/**
 * Remove at command. Removes a worker at the position as a collection index.
 */
public class Remove_At extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Remove_At() {
        super("remove_at", "remove an element at a give position");
    }

    /**
     * Removes a worker at a given position. If the position is incorrect execution will be stopped.
     * Uses remove method of super class.
     *
     * @param workers the collection to work with
     * @param args    the index to remove at
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        int index = -1;
        try {
            index = Integer.parseInt(args);
            if (index < 0 || index > workers.size()) {
                System.out.println("Invalid index");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index");
            return;
        }
        workers.remove(index);
        System.out.println("Worker removed successfully!\n");
    }
}
