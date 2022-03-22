package commands.concrete;

import collection.WorkerColManager;
import commands.Command;

/**
 * Remove at command. Removes a worker at the position as a collection index.
 */
public class Remove_At extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Remove_At(WorkerColManager colManager) {
        super("remove_at", "remove an element at a give position");
        this.colManager = colManager;
    }

    /**
     * Removes a worker at a given position. If the position is incorrect execution will be stopped.
     * Uses remove method of super class.
     *
     * @param args the index to remove at
     */
    @Override
    public void action(String args) {
        int index = -1;
        try {
            index = Integer.parseInt(args);
            if (index < 0 || index >= colManager.getSize()) {
                System.out.println("Invalid index");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index");
            return;
        }
        colManager.removeWorkerAt(index);
        System.out.println("Worker removed successfully!\n");
    }
}
