package factory;

import collection.entity.*;
import exceptions.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Factory generating objects of type Worker. It has setters with some validation within and
 * one getter which returns a new object. It used basically during the parsing workers from file.
 *
 * @see Worker
 */
public class WorkersFactory {
    /**
     * An emerging object
     */
    private final Worker worker = new Worker();

    /**
     * Sets workers ID, that must be a positive number.
     * If it's incorrect, the new exception will be thrown.
     *
     * @param value the worker's ID
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public WorkersFactory setID(String value) throws NumberFormatException {
        try {
            int intValue = Integer.parseInt(value.trim());
            if (intValue <= 0) throw new NumberFormatException
                    ("The ID of the worker is not correct");
            worker.setId(intValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("The ID of the worker is not correct");
        }
        return this;
    }

    /**
     * Sets worker's name, that can't be empty.
     * If it's incorrect, the new exception will be thrown.
     *
     * @param value the worker's name
     * @return the same factory
     * @throws InvalidInputException if an input value is incorrect
     */
    public WorkersFactory setName(String value) throws InvalidInputException {
        if (value.trim().equals("")) throw new InvalidInputException
                ("The worker's name is nor correct");
        worker.setName(value.trim());
        return this;
    }

    /**
     * Sets worker's coordinates.
     *
     * @param coordinates worker's coordinates
     * @return the same factory
     */
    public WorkersFactory setCoordinates(Coordinates coordinates) {
        worker.setCoordinates(coordinates);
        return this;
    }

    /**
     * Sets worker's creation date. The value is parsing from the string.
     *
     * @param value the worker's creation date
     * @return the same factory
     * @throws DateTimeParseException if an input value is incorrect
     */
    public WorkersFactory setCreationDate(String value) throws DateTimeParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(value);
        worker.setCreationDate(localDateTime);
        return this;
    }

    /**
     * Sets worker's salary, that must be a positive number.
     * If it's incorrect, the new exception will be thrown.
     *
     * @param value the worker's salary
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public WorkersFactory setSalary(String value) throws NumberFormatException {
        try {
            long longValue = Long.parseLong(value.trim());
            if (longValue <= 0) throw new NumberFormatException
                    ("The salary of the worker is not correct");
            worker.setSalary(longValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("The salary of the worker is not correct");
        }
        return this;
    }

    /**
     * Sets worker's position. If this position doesn't exist,
     * the new exception will be thrown.
     *
     * @see Position
     * @param value the worker's position
     * @return the same factory
     * @throws InvalidInputException if an input position doesn't exist
     */
    public WorkersFactory setPosition(String value) throws InvalidInputException {
        String newValue = value.trim().toUpperCase(Locale.ROOT);
        for (Position values : Position.values()) {
            if (values.toString().equals(newValue)) {
                worker.setPosition(values);
                return this;
            }
        }
        throw new InvalidInputException("Worker position type given in the file doesn't exist");
    }

    /**
     * Sets worker's status. If this status doesn't exist,
     * the new exception will be thrown.
     *
     * @see Status
     * @param value thr worker's status
     * @return the same factory
     * @throws InvalidInputException if an input status doesn't exist
     */
    public WorkersFactory setStatus(String value) throws InvalidInputException {
        String newValue = value.trim().toUpperCase(Locale.ROOT);
        for (Status values : Status.values()) {
            if (values.toString().equals(newValue)) {
                worker.setStatus(values);
                return this;
            }
        }
        throw new InvalidInputException("Worker status type given in the file doesn't exist");
    }

    /**
     * Sets worker's personality.
     *
     * @param person the person's personality
     * @return the same factory
     */
    public WorkersFactory setPerson(Person person) {
        worker.setPerson(person);
        return this;
    }

    /**
     * Returns a finished object
     *
     * @return a finished worker
     */
    public Worker getWorker() {
        return worker;
    }
}