package commands.concrete;

import collection.entity.Worker;
import collection.WorkersCollection;
import commands.Command;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Save command. Saves the collection in a file whose path is bound to the collection.
 * Data is written in csv format.
 */
public class Save extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Save() {
        super("save", "save the collection to a file");
    }

    /**
     * Calls saveCollection method. If an error occurs during execution, a message will be displayed
     * and the collection will not be saved.
     *
     * @param workers the collection to save
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        try {
            saveCollection(workers);
        } catch (IOException e) {
            System.out.println("Failed to save the collection to file");
        }
        System.out.println("collection saved successfully");
    }

    /**
     * Saves collection to a file using BufferedOutputStream. Data is written in csv format.
     *
     * @param collection the collection to save
     * @throws IOException if an output exception occurs
     */
    private void saveCollection(WorkersCollection collection) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(collection.getFilePath(), false));
        try (bos) {
            String fields = "id,name,coordinates_x,coordinates_y," +
                    "creationDate,salary,position,status,person_height,person_passportID," +
                    "person_location_x,person_location_y,person_location_z,person_location_name" + "\n";

            bos.write(fields.getBytes());
            String values;
            for (Worker worker : collection) {
                values = worker.getId() + "," + worker.getName() + ","
                        + worker.getCoordinates().getX().toString() + ","
                        + worker.getCoordinates().getY() + ","
                        + worker.getCreationDate().toString() + ","
                        + worker.getSalary() + ","
                        + worker.getPosition().toString() + ","
                        + worker.getStatus().toString() + ","
                        + worker.getPerson().getHeight() + ","
                        + worker.getPerson().getPassportID() + ","
                        + worker.getPerson().getLocation().getX() + ","
                        + worker.getPerson().getLocation().getY() + ","
                        + worker.getPerson().getLocation().getZ() + ","
                        + worker.getPerson().getLocation().getName() + "\n";
                bos.write(values.getBytes());
                bos.flush();
            }
        } catch (IOException e) {
            throw new IOException("Collections has not saved");
        }
    }
}
