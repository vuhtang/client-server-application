package Fabrics;

import Collection.Entity.*;
import Exceptions.InvalidInputFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class WorkersFabric {
    private final Worker worker = new Worker();

    public WorkersFabric setID(String value) throws NumberFormatException{
        try {
            int intValue = Integer.parseInt(value);
            if (intValue <= 0) throw new NumberFormatException
                    ("The ID of the worker is not correct");
            worker.setId(intValue);
        } catch (NumberFormatException e){
            throw new NumberFormatException("The ID of the worker is not correct");
        }
        return this;
    }
    public WorkersFabric setName(String value) throws InvalidInputFormat{
        if (value.equals("")) throw new InvalidInputFormat
                ("The worker's name is nor correct");
        worker.setName(value);
        return this;
    }
    public WorkersFabric setCoordinates(Coordinates coordinates){
        worker.setCoordinates(coordinates);
        return this;
    }
    public WorkersFabric setCreationDate(String value) throws DateTimeParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(value);
        worker.setCreationDate(localDateTime);
        return this;
    }
    public WorkersFabric setSalary(String value) throws NumberFormatException {
        try{
            long longValue = Long.parseLong(value);
            if (longValue <= 0) throw new NumberFormatException
                    ("The salary of the worker is not correct");
            worker.setSalary(longValue);
        } catch (NumberFormatException e){
            throw new NumberFormatException("The salary of the worker is not correct");
        }
        return this;
    }

    public WorkersFabric setPosition(String value) throws InvalidInputFormat {
        String newValue = value.toUpperCase(Locale.ROOT);
        for (Position values: Position.values()) {
            if (values.toString().equals(newValue)){
                worker.setPosition(values);
                return this;
            }
        }
        throw new InvalidInputFormat("Worker position type given in the file doesn't exist");
    }
    public WorkersFabric setStatus(String value) throws InvalidInputFormat{
        String newValue = value.toUpperCase(Locale.ROOT);
        for (Status values: Status.values()){
            if (values.toString().equals(newValue)){
                worker.setStatus(values);
                return this;
            }
        }
        throw new InvalidInputFormat("Worker status type given in the file doesn't exist");
    }
    public WorkersFabric setPerson(Person person){
        worker.setPerson(person);
        return this;
    }
    public Worker getWorker(){
        return worker;
    }
}
