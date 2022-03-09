package Commands.concrete;

import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.Command;


public class Show extends Command {
    public Show(){
        super("show", "print all elements of collection to standard output");
    }
    @Override
    public void action(WorkersCollection workers, String args){
        System.out.println("\nWorkers in collection:");
        for (Worker worker: workers) {
            System.out.println("[ ID = " + worker.getId() + ", Name = " + worker.getName()
            + ", X_coordinate = " + worker.getCoordinates().getX() +
                    ", Y_coordinate = " + worker.getCoordinates().getY() + ", CreationDate = "
            + worker.getCreationDate().getDayOfMonth() + "." + worker.getCreationDate().getDayOfMonth()
                    + "." + worker.getCreationDate().getYear() + ", Salary = " + worker.getSalary()
            + ", Position = " + worker.getPosition() + ", Status = " + worker.getStatus()
            + ", Person's height = " + worker.getPerson().getHeight() + ", Person's passportID = "
            + ", X_coordinate of person's location = " + worker.getPerson().getLocation().getX()
            + ", Y_coordinate of person's location = " + worker.getPerson().getLocation().getY()
            + ", Z_coordinate of person's location = " + worker.getPerson().getLocation().getZ()
            + ", Name of person's location = " + worker.getPerson().getLocation().getName() + "]");
        }
        System.out.println();
    }
}
