package collection;

import collection.entity.Coordinates;
import collection.entity.Position;
import collection.entity.Worker;
import repository.SqlManager;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Worker collection class manager. Contains a collection and commands to work with.
 */
public class WorkerColManager {
    /**
     * Collection to work with.
     */
    private final WorkerCollection collection;
    private final SqlManager sqlManager;
    /**
     * Initializes collection manager to the given collection.
     *
     * @param collection collection to work with
     */
    public WorkerColManager(WorkerCollection collection, SqlManager sqlManager) {
        this.collection = collection;
        this.sqlManager = sqlManager;
    }

    /**
     * Adds all workers from the given collection, elements of which extends from Worker.
     *
     * @param c collection of elements to add
     */
    public void addAllWorkers(Collection<? extends Worker> c) {
        collection.addAll(c);
    }

    public Worker getWorkerByIndex(int index) {
        return collection.get(index);
    }

    public Worker getWorkerByID(int id) {
        Optional<Worker> o = collection.stream().filter((p) -> p.getId() == id).findAny();
        return o.orElse(null);
    }

    public SqlManager getSqlManager(){
        return sqlManager;
    }

    public void addWorker(Worker worker) {
        collection.add(worker);
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

    public void removeWorkerById(int id) {
        collection.removeIf(worker -> worker.getId() == id);
    }

    public List<Worker> getAllLower(Worker worker) {
        return collection.stream().filter((p) -> p.compareTo(worker) < 0).toList();
    }

    public boolean checkId(int id) {
        return collection.stream().anyMatch((p) -> p.getId() == id);
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
