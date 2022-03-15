package commands.concrete;

import collection.entity.Worker;
import collection.WorkersCollection;
import commands.Command;

/**
 * Remove by ID command. Removes a worker with given ID. Only one worker will be removed
 * since the ID is unique for each worker in the given collection.
 */
public class Remove_By_ID extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Remove_By_ID() {
        super("remove_by_id", "remove worker by id");
    }

    /**
     * Removes worker by given ID. If the ID is incorrect execution will be stopped.
     *
     * @param workers the collection to work with
     * @param args    the ID of worker to be removed
     */
    public void action(WorkersCollection workers, String args) {
        int id = -1;
        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id of the worker");
            return;
        }
        for (Worker worker : workers) {
            if (worker.getId() == id){
                workers.remove(worker);
                System.out.println("Worker removed successfully!\n");
                return;
            }
        }
        System.out.println("A worker with such id has not been found");
    }
}
