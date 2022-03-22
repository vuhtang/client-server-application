package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Position;
import commands.Command;

import java.util.Locale;

/**
 * Filter by position command. Selects workers from a collection
 * with a given position and displays the resulting list.
 */
public class Filter_By_Position extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Filter_By_Position(WorkerColManager colManager) {
        super("filter_by_position",
                "display elements whose position field value is equal to the given one");
        this.colManager = colManager;
    }

    /**
     * Selects workers from a collection with a given position. If such position
     * does not exist, then the execution will be stopped. Then prints a list of workers with
     * this position.
     *
     * @param args the position to filter by
     */
    @Override
    public void action(String args) {
        Position position;
        try {
            position = Position.valueOf(args.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            System.out.println("No such position exists. Existing positions: " +
                    "LABORER, HEAD_OF_DIVISION, MANAGER_OF_CLEANING");
            return;
        }
        System.out.println(colManager.getWorkersByPosition(position));
    }
}
