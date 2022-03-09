package Commands.concrete;

import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.Command;

public class Average_Of_Salary extends Command{
    public Average_Of_Salary(){
        super("average_of_salary",
                "get the average value of the salary for all items in the collection");
    }

    @Override
    public void action(WorkersCollection workers, String args) {
        System.out.println("\nAverage of salary: " +
                workers.stream().mapToLong(Worker::getSalary).average().getAsDouble() + "\n");
    }
}
