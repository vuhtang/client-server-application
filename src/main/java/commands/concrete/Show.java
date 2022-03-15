package commands.concrete;

import collection.entity.Worker;
import collection.WorkersCollection;
import commands.Command;

/**
 * Show command. Displays all collection elements with their characteristics.
 */
public class Show extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Show() {
        super("show", "print all elements of collection to standard output");
    }

    /**
     * Shows all collection elements with values of all their fields.
     *
     * @param workers the collection to show
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        System.out.println("\nWorkers in collection:");
        for (Worker worker : workers) {
            System.out.println("[ ID = " + worker.getId() + ", Name = " + worker.getName()
                    + ", X_coordinate = " + worker.getCoordinates().getX() +
                    ", Y_coordinate = " + worker.getCoordinates().getY() + ", CreationDate = "
                    + worker.getCreationDate().getDayOfMonth() + "." + worker.getCreationDate().getMonth().getValue()
                    + "." + worker.getCreationDate().getYear() + ", Salary = " + worker.getSalary()
                    + ", Position = " + worker.getPosition() + ", Status = " + worker.getStatus()
                    + ", Person's height = " + worker.getPerson().getHeight() + ", Person's passportID = "
                    + ", X_coordinate of person's location = " + worker.getPerson().getLocation().getX()
                    + ", Y_coordinate of person's location = " + worker.getPerson().getLocation().getY()
                    + ", Z_coordinate of person's location = " + worker.getPerson().getLocation().getZ()
                    + ", Name of person's location = " + worker.getPerson().getLocation().getName() + "]");
        }
    }
}
