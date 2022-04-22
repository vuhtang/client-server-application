package collection;

import collection.entity.Worker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * WorkerCollection is a class that implements a collection of workers. The collection is
 * associated with one specific file, the path to which is set when collection is created.
 * <p>
 * It extends from an ArrayList with a Worker type parameter. This allows you to use already
 * existing methods from ArrayList or in some cases just override them by calling the super class
 * method and adding new functionality.
 */
public class WorkerCollection extends ArrayList<Worker> {
    /**
     * The collection file path. Can't be changed because each collection is associated
     * with only one specific file.
     */
    private final String filePath;
    /**
     * The collection initialization date.
     */
    private final LocalDateTime initializationDate;
    /**
     * The set of already used ID of workers of this collection.
     */
    private final HashSet<Integer> idSet = new HashSet<>();

    {
        initializationDate = LocalDateTime.now();
    }

    /**
     * Creates a new collection with workers from a given file.
     *
     * @param path path to the data file to create a new collection
     */
    public WorkerCollection(String path) {
        super();
        filePath = path;
    }

    /**
     * Adds all workers from the list and remembers ID of added workers.
     * Used only for adding objects to an empty collection for the first time.
     *
     * @param c the list of workers to add
     * @return true if this collection changed as a result of the call
     * @see ArrayList#addAll(Collection)
     */
    @Override
    public boolean addAll(Collection<? extends Worker> c) {
        for (Worker worker : c) {
            idSet.add(worker.getId());
        }
        return super.addAll(c);
    }

    /**
     * Adds new ID to the set if it is not already present.
     *
     * @param id ID to be added
     * @return true if set did not already contain this ID
     * @see HashSet#add(Object)
     */
    public boolean addID(int id) {
        return idSet.add(id);
    }

    public String getFilePath() {
        return filePath;
    }

    public LocalDateTime getChangedDate() {
        return initializationDate;
    }
}
