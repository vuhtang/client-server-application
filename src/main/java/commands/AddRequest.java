package commands;

import collection.entity.*;
import exceptions.InvalidInputException;
import factory.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class with only one method requesting new worker. This method invites you to enter
 * all fields of the worker sequentially with the possibility of canceling the entry.
 */
public class AddRequest {
    /**
     * The list of requests for input fields.
     */
    private final List<String> requests = new ArrayList<>();

    {
        requests.add("Enter the x coordinate of the worker: (or type \"quit\" to stop typing)");//0
        requests.add("Enter the y coordinate of the worker: (or type \"quit\" to stop typing)");//1
        requests.add("Enter the x coordinate of the person's location:" +
                " (or type \"quit\" to stop typing)");//2
        requests.add("Enter the y coordinate of the person's location: " +
                "(or type \"quit\" to stop typing)");//3
        requests.add("Enter the z coordinate of the person's location: " +
                "(or type \"quit\" to stop typing)");//4
        requests.add("Enter the name coordinate of the person's location: " +
                "(or type \"quit\" to stop typing)");//5
        requests.add("Enter the height of the person: (or type \"quit\" to stop typing)");//6
        requests.add("Enter the passportID of the person: (or type \"quit\" to stop typing)");//7
        requests.add("Enter the name of the worker: (or type \"quit\" to stop typing)");//8
        requests.add("Enter the salary of the worker: (or type \"quit\" to stop typing)");//9
        requests.add("Enter the position of the worker: (or type \"quit\" to stop typing)"
                + "\nAvailable positions: " + Arrays.toString(Position.values()));//10
        requests.add("Enter the status of the worker: (or type \"quit\" to stop typing)"
                + "\nAvailable statuses: " + Arrays.toString(Status.values()));//11
    }

    /**
     * Requests new worker using console input. User always can enter "quit" to cancel adding.
     * Prints one request from the list of requests, reads the answer if it'll be correct
     * or offers to enter it again.
     *
     * @return the new worker without ID
     */
    public Worker requestWorker() {
        Worker worker = null;
        int counter = 0;
        String line;
        Scanner scanner = new Scanner(System.in);
        CoordinatesFactory cfactory = new CoordinatesFactory();
        LocationFactory lfactory = new LocationFactory();
        PersonsFactory pfactiry = new PersonsFactory();
        WorkersFactory wfactory = new WorkersFactory();
        while (worker == null) {
            try {
                System.out.println(requests.get(counter));
                line = scanner.nextLine();
                if (line.trim().equals("quit")) {
                    System.out.println("Adding cancelled");
                    return null;
                }
                switch (counter) {
                    case 0 -> {
                        cfactory.setX(line);
                        counter++;
                    }
                    case 1 -> {
                        cfactory.setY(line);
                        counter++;
                        wfactory.setCoordinates(cfactory.getCoordinates());
                    }
                    case 2 -> {
                        lfactory.setX(line);
                        counter++;
                    }
                    case 3 -> {
                        lfactory.setY(line);
                        counter++;
                    }
                    case 4 -> {
                        lfactory.setZ(line);
                        counter++;
                    }
                    case 5 -> {
                        lfactory.setName(line);
                        counter++;
                        pfactiry.setLocation(lfactory.getLocation());
                    }
                    case 6 -> {
                        pfactiry.setHeight(line);
                        counter++;
                    }
                    case 7 -> {
                        pfactiry.setPassportID(line);
                        counter++;
                        wfactory.setPerson(pfactiry.getPerson());
                    }
                    case 8 -> {
                        wfactory.setName(line);
                        counter++;
                    }
                    case 9 -> {
                        wfactory.setSalary(line);
                        counter++;
                    }
                    case 10 -> {
                        wfactory.setPosition(line);
                        counter++;
                    }
                    case 11 -> {
                        wfactory.setStatus(line);
                        counter++;
                        worker = wfactory.getWorker();
                    }
                }
            } catch (NumberFormatException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        worker.setCreationDate(LocalDateTime.now());
        return worker;
    }
}
