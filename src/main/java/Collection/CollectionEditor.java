package Collection;

import Collection.Entity.Worker;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CollectionEditor {
    WorkersCollection collection;
    public CollectionEditor(WorkersCollection collection){
        this.collection = collection;
    }
    public void saveCollection() throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(collection.getFilePath(), false));
        try(bos){
            String fields = "id,name,coordinates_x,coordinates_y," +
                    "creationDate,salary,position,status,person_height,person_passportID," +
                    "person_location_x,person_location_y,person_location_z,person_location_name" + "\n";

            bos.write(fields.getBytes());
            String values;
            for (Worker worker: collection){
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
