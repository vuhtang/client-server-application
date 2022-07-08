package parser;

import collection.entity.*;
import exceptions.*;
import factory.*;
import java.time.format.DateTimeParseException;


/**
 * Class is for reading workers from string massive, validation occurs during the reading,
 * in case of invalid value of some field, an error message will be displayed.
 * Used when initially loading data from the server.
 */
public class WorkerReader {

    /**
     * Reads worker from string massive of fields. Uses factories with validation in.
     *
     * @param values 15 string values of fields
     * @return a new worker
     * @throws InvalidInputException if any value fails validation
     */
    public static Worker readWorker(String[] values) throws InvalidInputException {
        Worker worker;
        try {
            Location location = new LocationFactory().setName(values[13]).setZ(values[12])
                    .setY(values[11]).setX(values[10]).getLocation();
            Person person = new PersonsFactory().setPassportID(values[9])
                    .setHeight(values[8]).setLocation(location).getPerson();
            Coordinates coordinates = new CoordinatesFactory().setX(values[2])
                    .setY(values[3]).getCoordinates();
            worker = new WorkersFactory().setID(values[0])
                    .setName(values[1]).setCoordinates(coordinates)
                    .setCreationDate(values[4]).setSalary(values[5])
                    .setPosition(values[6]).setStatus(values[7])
                    .setPerson(person).getWorker();
            worker.setOwner(values[14]);

        } catch (NumberFormatException | InvalidInputException | DateTimeParseException e) {
            throw new InvalidInputException(e.getMessage());
        }
        return worker;
    }
}