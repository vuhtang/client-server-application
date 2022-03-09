package Commands.concrete;

import Collection.Entity.Coordinates;
import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.Command;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Group_Counting_By_Coordinates extends Command {
    public Group_Counting_By_Coordinates() {
        super("group_counting_by_coordinates",
                "group the elements of the collection by the value of the field coordinates," +
                        " display the number of elements in each group");
    }

    @Override
    public void action(WorkersCollection workers, String args) {
        Map<Coordinates, List<Worker>> map = workers.stream()
                .collect(Collectors.groupingBy(Worker::getCoordinates));
        for(Map.Entry<Coordinates, List<Worker>> item : map.entrySet()){
            System.out.println(item.getKey() + " : " + item.getValue().toString());
        }
    }
}
