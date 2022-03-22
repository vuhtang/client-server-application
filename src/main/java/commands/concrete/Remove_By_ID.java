package commands.concrete;

import collection.WorkerColManager;
import commands.Command;

/**
 * Remove by ID command. Removes a worker with given ID. Only one worker will be removed
 * since the ID is unique for each worker in the given collection.
 */
public class Remove_By_ID extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Remove_By_ID(WorkerColManager colManager) {
        super("remove_by_id", "remove worker by id");
        this.colManager = colManager;
    }

    /**
     * Removes worker by given ID. If the ID is incorrect execution will be stopped.
     *
     * @param args the ID of worker to be removed
     */
    public void action(String args) {
        int id = -1;
        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id of the worker");
            return;
        }
        if (colManager.removeWorkerById(id)) System.out.println("Worker removed successfully");
        else System.out.println("A worker with such id has not been found");
    }
}
