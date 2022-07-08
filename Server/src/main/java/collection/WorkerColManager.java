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
     * @param sqlManager database manager to work with
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

    public SqlManager getSqlManager() {
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

    public List<String> getInfo() {
        List<String> info = new ArrayList<>();
        info.add(collection.getClass().getSimpleName());
        info.add(collection.getChangedDate().getDayOfMonth() +
                "." + collection.getChangedDate().getMonth().getValue() + "." + collection.getChangedDate().getYear() +
                " " + collection.getChangedDate().toLocalTime());
        return info;
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
        if (collection.isEmpty()) return list;
        else {
            for (Worker worker : collection) {
                list.add(worker.getId() + "," + worker.getName() + ","
                        + worker.getCoordinates().getX() + "," + worker.getCoordinates().getY() + ","
                        + worker.getCreationDate().toString() + "," + worker.getSalary() + ","
                        + worker.getPosition() + "," + worker.getStatus() + ","
                        + worker.getPerson().getHeight() + "," + worker.getPerson().getPassportID() + ","
                        + worker.getPerson().getLocation().getX() + "," + worker.getPerson().getLocation().getY() + ","
                        + worker.getPerson().getLocation().getZ() + "," + worker.getPerson().getLocation().getName() + ","
                        + worker.getOwner());
            }
        }
        return list;
    }
}