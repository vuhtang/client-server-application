package Commands.concrete;

import Collection.Entity.Position;
import Collection.WorkersCollection;
import Commands.Command;

import java.util.Locale;

public class Filter_By_Position extends Command {
    public Filter_By_Position() {
        super("filter_by_position",
                "display elements whose position field value is equal to the given one");
    }

    @Override
    public void action(WorkersCollection workers, String args) {
        Position position = null;
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
