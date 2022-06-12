package commands;

import collection.entity.*;
import exceptions.InvalidInputException;
import factory.*;

import java.time.LocalDateTime;

/**
 * Class with only one method requesting new worker. This method invites you to enter
 * all fields of the worker sequentially with the possibility of canceling the entry.
 */
public class AddRequest {
    private final WorkersFactory workersFactory = new WorkersFactory();
    private final CoordinatesFactory coordinatesFactory = new CoordinatesFactory();
    private final PersonsFactory personsFactory = new PersonsFactory();
    private final LocationFactory locationFactory = new LocationFactory();

    public Worker getRowWorker(String[] values) throws NumberFormatException, InvalidInputException {
        Coordinates coordinates = coordinatesFactory.setX(values[0]).setY(values[1]).getCoordinates();
        Location location = locationFactory.setX(values[2]).setY(values[3])
                .setZ(values[4]).setName(values[5]).getLocation();
        Person person = personsFactory.setHeight(values[6]).setPassportID(values[7]).setLocation(location).getPerson();
        return workersFactory.setName(values[8]).setSalary(values[9]).setPosition(values[10])
                .setStatus(values[11]).setCreationDate(LocalDateTime.now().toString()).setPerson(person)
                .setCoordinates(coordinates).getWorker();
    }
}
