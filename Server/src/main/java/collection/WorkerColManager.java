package collection;

import collection.entity.Coordinates;
import collection.entity.Position;
import collection.entity.Worker;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Worker collection class manager. Contains a collection and commands to work with.
 */
public class WorkerColManager implements Serializable {
    /**
     * Collection to work with.
     */
    private final WorkerCollection collection;

    /**
     * Initializes collection manager to the given collection.
     *
     * @param collection collection to work with
     */
    public WorkerColManager(WorkerCollection collection) {
        this.collection = collection;
    }

    /**
     * Adds all workers from the given collection, elements of which extends from Worker.
     *
     * @param c collection of elements to add
     */
    public void addAllWorkers(Collection<? extends Worker> c) {
        collection.addAll(c);
    }

    public void addWorker(Worker worker) {
        collection.add(worker);
    }

    /**
     * Adds new ID to the collection's memory if it is not already present.
     *
     * @param id ID to be added
     * @return true if collection's memory did not already contain this ID
     */
    public boolean addID(int id) {
        return collection.addID(id);
    }

    /**
     * Gives the average value of the salary for all items in the collection.
     *
     * @return average value of the salary
     */
    public Double averageOfSalary() {
        return collection.stream().mapToLong(Worker::getSalary).average().getAsDouble();
    }

    /**
     * Clears the collection.
     */
    public void clear() {
        collection.clear();
    }

    public List<Worker> getWorkersByPosition(Position position) {
        return collection.stream().filter(p -> p.getPosition() == position).toList();
    }

    /**
     * Gives workers with same coordinates.
     *
     * @return Map(coordinates, list of workers)
     */
    public Map<Coordinates, List<Worker>> getGroupsByCoordinates() {
        return collection.stream().collect(Collectors.groupingBy(Worker::getCoordinates));
    }

    public String getInfo() {
        return "\nCollection type: " + collection.getClass().getSimpleName()
                + "\nInitialization date: " + collection.getChangedDate().getDayOfMonth() + "."
                + collection.getChangedDate().getMonth().getValue() + "." + collection.getChangedDate().getYear()
                + "  " + collection.getChangedDate().toLocalTime()
                + "\nAmount of elements: " + collection.size() + "\n";
    }

    public void insertAt(int index, Worker worker) {
        collection.add(index, worker);
    }

    public int getSize() {
        return collection.size();
    }

    public void removeWorkerAt(int index) {
        collection.remove(index);
    }

    public boolean removeWorkerById(int id) {
        return collection.removeIf(worker -> worker.getId() == id);
    }

    public void removeAllLower(Worker worker) {
        List<Worker> list = collection.stream().filter((p) -> p.compareTo(worker) <= 0).toList();
        collection.removeAll(list);
    }

    public boolean saveCollection() {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(collection.getFilePath(), false))) {
            String fields = "id,name,coordinates_x,coordinates_y," +
                    "creationDate,salary,position,status,person_height,person_passportID," +
                    "person_location_x,person_location_y,person_location_z,person_location_name" + "\n";

            bos.write(fields.getBytes());
            String values;
            for (Worker worker : collection) {
                values = worker.getId() + "," + worker.getName() + ","
                        + worker.getCoordinates().getX().toString() + ","
                        + worker.getCoordinates().getY() + ","
                        + worker.getCreationDate().toString() + ","
                        + worker.getSalary() + ","
                        + worker.getPosition().toString() + ","
                        + worker.getStatus().toString() + ","
                        + worker.getPerson().getHeight() + ","
                        + worker.getPerson().getPassportID() + ","
                        + worker.getPerson().getLocation().getX() + ","
                        + worker.getPerson().getLocation().getY() + ","
                        + worker.getPerson().getLocation().getZ() + ","
                        + worker.getPerson().getLocation().getName() + "\n";
                bos.write(values.getBytes());
                bos.flush();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public List<String> showWorkers() {
        List<String> list = new ArrayList<>();
        if (collection.isEmpty()) {
            list.add("Collection is empty");
            return list;
        } else {
            list.add("Workers in collection:");
            for (Worker worker : collection) {
                list.add("[ ID = " + worker.getId() + ", Name = " + worker.getName()
                        + ", X_coordinate = " + worker.getCoordinates().getX() +
                        ", Y_coordinate = " + worker.getCoordinates().getY() + ", CreationDate = "
                        + worker.getCreationDate().getDayOfMonth() + "." + worker.getCreationDate().getMonth().getValue()
                        + "." + worker.getCreationDate().getYear() + ", Salary = " + worker.getSalary()
                        + ", Position = " + worker.getPosition() + ", Status = " + worker.getStatus()
                        + ", Person's height = " + worker.getPerson().getHeight() + ", Person's passportID = "
                        + worker.getPerson().getPassportID()
                        + ", X_coordinate of person's location = " + worker.getPerson().getLocation().getX()
                        + ", Y_coordinate of person's location = " + worker.getPerson().getLocation().getY()
                        + ", Z_coordinate of person's location = " + worker.getPerson().getLocation().getZ()
                        + ", Name of person's location = " + worker.getPerson().getLocation().getName() + "]");
            }
        }
        return list;
    }
}
