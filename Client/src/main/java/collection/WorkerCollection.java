package collection;

import collection.entity.Worker;
import java.util.*;

/**
 * Collection of workers class. Almost like ArrayList, but can be sorted.
 * <p>
 * It extends from an ArrayList with a Worker type parameter. This allows you to use already
 * existing methods from ArrayList or in some cases just override them by calling the super class
 * method and adding new functionality.
 */
public class WorkerCollection extends ArrayList<Worker> {

    public WorkerCollection() {
        super();
    }

    public WorkerCollection sortedById() {
        List<Worker> list = this.stream().sorted(Comparator.comparingInt(Worker::getId)).toList();
        this.clear();
        this.addAll(list);
        return this;
    }
}
