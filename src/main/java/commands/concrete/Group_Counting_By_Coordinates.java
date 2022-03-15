package commands.concrete;

import collection.entity.Coordinates;
import collection.entity.Worker;
import collection.WorkersCollection;
import commands.Command;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Group counting by coordinates command. Selects elements of collection with same coordinates
 * and displays received groups.
 */
public class Group_Counting_By_Coordinates extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Group_Counting_By_Coordinates() {
        super("group_counting_by_coordinates",
                "group the elements of the collection by the value of the field coordinates," +
                        " display the number of elements in each group");
    }

    /**
     * Groups workers from collection into groups and writes them down in a lists as a values in Map,
     * keys to which are coordinates of workers in appropriating list.
     *
     * @param workers the collection to work with
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        Map<Coordinates, List<Worker>> map = workers.stream()
                .collect(Collectors.groupingBy(Worker::getCoordinates));
        for (Map.Entry<Coordinates, List<Worker>> item : map.entrySet()) {
            System.out.println(item.getKey() + " : " + item.getValue().toString());
        }
    }
}
