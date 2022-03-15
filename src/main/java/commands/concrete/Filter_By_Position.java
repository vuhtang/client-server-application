package commands.concrete;

import collection.entity.Position;
import collection.WorkersCollection;
import commands.Command;

import java.util.Locale;

/**
 * Filter by position command. Selects workers from a collection
 * with a given position and displays the resulting list.
 */
public class Filter_By_Position extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Filter_By_Position() {
        super("filter_by_position",
                "display elements whose position field value is equal to the given one");
    }

    /**
     * Selects workers from a collection with a given position. If such position
     * does not exist, then the execution will be stopped. Then prints a list of workers with
     * this position.
     *
     * @param workers the collection to work with
     * @param args the position to filter by
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        Position position;
        try{
            position = Position.valueOf(args.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e){
            System.out.println("No such position exists");
            return;
        }
        Position finalPosition = position;
        System.out.println(workers.stream().filter(p -> p.getPosition() == finalPosition).toList());
    }
}
