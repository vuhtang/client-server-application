package collection;

import collection.entity.Worker;

import java.time.LocalDateTime;
import java.util.*;

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
     * The collection initialization date.
     */
    private final LocalDateTime initializationDate;

    {
        initializationDate = LocalDateTime.now();
    }

    public WorkerCollection() {
        super();
    }

    public WorkerCollection sortedById() {
        List<Worker> list = this.stream().sorted(Comparator.comparingInt(Worker::getId)).toList();
        this.clear();
        this.addAll(list);
        return this;
    }

    public LocalDateTime getChangedDate() {
        return initializationDate;
    }
}
