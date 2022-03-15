package parser;

import collection.entity.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import exceptions.*;
import factory.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class WorkersReader {
    private Location location;
    private Person person;
    private Coordinates coordinates;
    private Worker worker;

    public static List<Worker> readWorkers(String path1) throws IOException,
            InvalidInputException {
        Path path = Paths.get(path1);
        try {
            Scanner scanner = new Scanner(path);
        } catch (IOException e) {
            throw new IOException("No such file or directory");
        }
        Scanner scanner = new Scanner(path);
        List<Worker> list = new ArrayList<>();
        WorkersReader reader = new WorkersReader();
        if (!scanner.hasNext()) throw new InvalidInputException("The input file is empty");
        scanner.nextLine();
        if (!scanner.hasNext()) throw new InvalidInputException("The input file has no data");
        while (scanner.hasNext()) {
            list.add(reader.readWorker(scanner.nextLine()));
        }
        return list;
    }

    private Worker readWorker(String line) throws InvalidInputException {
        List<String> fields = Arrays.asList(line.split(","));
        if (fields.size() != 14) throw new InvalidInputException
                ("Expected 14 values in file, received: " + fields.size());
        else {
            try {
                location = new LocationFactory().setName(fields.get(13)).setZ(fields.get(12))
                        .setY(fields.get(11)).setX(fields.get(10)).getLocation();
                person = new PersonsFactory().setPassportID(fields.get(9))
                        .setHeight(fields.get(8)).setLocation(location).getPerson();
                coordinates = new CoordinatesFactory().setX(fields.get(2))
                        .setY(fields.get(3)).getCoordinates();
                worker = new WorkersFactory().setID(fields.get(0))
                        .setName(fields.get(1)).setCoordinates(coordinates)
                        .setCreationDate(fields.get(4)).setSalary(fields.get(5))
                        .setPosition(fields.get(6)).setStatus(fields.get(7))
                        .setPerson(person).getWorker();

            } catch (NumberFormatException | InvalidInputException | DateTimeParseException e) {
                throw new InvalidInputException(e.getMessage());
            }
        }
        return worker;
    }
}
