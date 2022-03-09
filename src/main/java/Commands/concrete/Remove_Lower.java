package Commands.concrete;

import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.AddRequest;
import Commands.Command;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Remove_Lower extends Command {
    public Remove_Lower(){
        super("remove_lower", "remove from the collection all elements " +
                "smaller than the given one");
    }

    @Override
    public void action(WorkersCollection workers, String args) {
        Worker worker = new AddRequest().requestWorker();
        workers.add(worker);
        Collections.sort(workers);
        List<Worker> list = workers.stream().filter((p) -> p.compareTo(worker) <= 0).toList();
        workers.removeAll(list);
    }
}
