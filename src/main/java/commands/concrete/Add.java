package commands.concrete;

import collection.entity.*;
import collection.WorkersCollection;
import commands.AddRequest;
import commands.Command;

/**
 * Add command. It adds new worker to the given collection,
 * worker data is entered in the console sequentially.
 */
public class Add extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Add() {
        super("add", "add a new element to the collection");
    }

    /**
     * Adds new worker to the given collection. During work, it makes a request to enter
     * data about the worker through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param workers the collection to add the worker to
     * @param args    an empty line, as an imperfection of the program model
     * @see AddRequest
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        int i = 1;
        while (!workers.addID(i)) {
            i += 1;
        }
        worker.setId(i);
        workers.add(worker);
        System.out.println("Worker added successfully!\n");
    }
}