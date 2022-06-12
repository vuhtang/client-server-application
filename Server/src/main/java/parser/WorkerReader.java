package parser;

import collection.entity.*;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import exceptions.*;
import factory.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class is for reading workers from file, validation occurs during the reading,
 * in case of invalid value of some field, an error message will be displayed
 * with the line number in which it was found.
 */
public class WorkerReader {

    /**
     * Reads worker from string massive of fields. Uses factories with validation in.
     *
     * @param values 14 string values of fields
     * @return a new worker
     * @throws InvalidInputException if any value fails validation
     */
    public static Worker readWorker(String[] values) throws InvalidInputException {
        Worker worker;
        try {
            Location location = new LocationFactory().setName(values[14]).setZ(values[13])
                    .setY(values[12]).setX(values[11]).getLocation();
            Person person = new PersonsFactory().setPassportID(values[10])
                    .setHeight(values[9]).setLocation(location).getPerson();
            Coordinates coordinates = new CoordinatesFactory().setX(values[3])
                    .setY(values[4]).getCoordinates();
            worker = new WorkersFactory().setID(values[1])
                    .setName(values[2]).setCoordinates(coordinates)
                    .setCreationDate(values[5]).setSalary(values[6])
                    .setPosition(values[7]).setStatus(values[8])
                    .setPerson(person).getWorker();
            worker.setOwner(values[15]);

        } catch (NumberFormatException | InvalidInputException | DateTimeParseException e) {
            throw new InvalidInputException(e.getMessage());
        }
        return worker;
    }
}
