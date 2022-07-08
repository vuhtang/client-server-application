package collection;

import collection.entity.Worker;

import java.io.Serializable;
import java.util.*;

/**
 * Worker collection class manager. Contains a collection and commands to work with.
 */
public class WorkerColManager implements Serializable {
    /**
     * Collection to work with.
     */
    private final WorkerCollection collection;

    public WorkerColManager(WorkerCollection collection) {
        this.collection = collection;
    }

    public Worker getWorkerByID(int id) {
        Optional<Worker> o = collection.stream().filter((p) -> p.getId() == id).findAny();
        return o.orElse(null);
    }

    public boolean checkId(int id) {
        return collection.stream().anyMatch(worker -> worker.getId() == id);
    }

    /**
     * Gives data for the table
     *
     * @return "row" data for table model
     */
    public Object[][] getRowData() {
        Object[][] rowData = new Object[getSize()][15];
        Worker worker;
        for (int i = 0; i < getSize(); i++) {
            worker = collection.get(i);
            rowData[i][0] = worker.getId();
            rowData[i][1] = worker.getName();
            rowData[i][2] = worker.getCoordinates().getX();
            rowData[i][3] = worker.getCoordinates().getY();
            rowData[i][4] = worker.getCreationDate();
            rowData[i][5] = worker.getSalary();
            rowData[i][6] = worker.getPosition();
            rowData[i][7] = worker.getStatus();
            rowData[i][8] = worker.getPerson().getHeight();
            rowData[i][9] = worker.getPerson().getPassportID();
            rowData[i][10] = worker.getPerson().getLocation().getX();
            rowData[i][11] = worker.getPerson().getLocation().getY();
            rowData[i][12] = worker.getPerson().getLocation().getZ();
            rowData[i][13] = worker.getPerson().getLocation().getName();
            rowData[i][14] = worker.getOwner();
        }
        return rowData;
    }

    /**
     * Gives field names for the table
     *
     * @return string field names
     */
    public String[] getRowFieldNames() {
        return new String[]{"id", "name", "coordinateX", "coordinateY", "creationDate",
                "salary", "position", "status", "personHeight",
                "personPassportId", "personLocationX", "personLocationY", "personLocationZ", "personLocationName",
                "owner"};
    }

    /**
     * Clears the collection.
     */
    public void clear() {
        collection.clear();
    }

    public int getSize() {
        return collection.size();
    }

    public List<Worker> getWorkers() {
        return collection.stream().toList();
    }
}