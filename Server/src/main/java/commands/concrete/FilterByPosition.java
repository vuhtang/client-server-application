package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Position;
import collection.entity.Worker;
import commands.Command;
import transferring.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Filter by position command. Selects workers from a collection
 * with a given position and displays the resulting list.
 */
public class FilterByPosition extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    public FilterByPosition(WorkerColManager colManager) {
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
    public List<String> action(String args, Worker worker, Token token) {
        Position position;
        List<String> response = new ArrayList<>();
        try {
            position = Position.valueOf(args.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            response.add("No such position exists. Existing positions: " +
                    "LABORER, HEAD_OF_DIVISION, MANAGER_OF_CLEANING");
            return response;
        }
        List<Worker> list = colManager.getWorkersByPosition(position);
        response = list.stream().map(Worker::toString).collect(Collectors.toList());
        return response;
    }
}
