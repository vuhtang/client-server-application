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
     * Reads workers from file along its path.
     *
     * @param path path to file
     * @return the list of workers from file
     * @throws IOException  if an input exception occurs
     * @throws CsvException if there is invalid data in the file
     */
    public static List<Worker> readWorkers(String path) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(path));
        List<String[]> myEntries = reader.readAll();
        int errorLineNumber = 1;
        if (myEntries.get(0)[0].startsWith("id")) {
            errorLineNumber += 1;
            myEntries.remove(0);
        }
        WorkerReader workerReader = new WorkerReader();
        List<Worker> workerList = new ArrayList<>();
        for (String[] line : myEntries) {
            if (line.length != 14) {
                CsvException e = new CsvException("Expected 14 values in file, received: " + line.length);
                e.setLineNumber(myEntries.indexOf(line) + errorLineNumber);
                throw e;
            }
            try {
                workerList.add(workerReader.readWorker(line));
            } catch (InvalidInputException e) {
                CsvException e1 = new CsvException(e.getMessage());
                e1.setLineNumber(myEntries.indexOf(line) + errorLineNumber);
                throw e1;
            }
        }
        return workerList;
    }

    /**
     * Reads worker from string massive of fields. Uses factories with validation in.
     *
     * @param values 14 string values of fields
     * @return a new worker
     * @throws InvalidInputException if any value fails validation
     */
    private Worker readWorker(String[] values) throws InvalidInputException {
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

        } catch (NumberFormatException | InvalidInputException | DateTimeParseException e) {
            throw new InvalidInputException(e.getMessage());
        }
        return worker;
    }
}
