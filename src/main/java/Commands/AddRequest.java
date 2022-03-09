package Commands;

import Collection.Entity.Position;
import Collection.Entity.Status;
import Collection.Entity.Worker;
import Exceptions.InvalidInputFormat;
import Fabrics.CoordinatesFabric;
import Fabrics.LocationFabric;
import Fabrics.PersonsFabric;
import Fabrics.WorkersFabric;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddRequest {
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
                + "\n Available statuses: " + Arrays.toString(Status.values()));//11
    }

    public Worker requestWorker() {
        Worker worker = null;
        int counter = 0;
        String line;
        Scanner scanner = new Scanner(System.in);
        CoordinatesFabric cfabric = new CoordinatesFabric();
        LocationFabric lfabric = new LocationFabric();
        PersonsFabric pfabric = new PersonsFabric();
        WorkersFabric wfabric = new WorkersFabric();
        while (worker == null) {
            try {
                System.out.println(requests.get(counter));
                line = scanner.nextLine();
                if (line.trim().equals("quit")) {
                    System.out.println("Adding cancelled");
                    return null;
                }
                switch (counter) {
                    case 0: {
                        cfabric.setX(line);
                        counter++;
                        break;
                    }
                    case 1: {
                        cfabric.setY(line);
                        counter++;
                        wfabric.setCoordinates(cfabric.getCoordinates());
                        break;
                    }
                    case 2: {
                        lfabric.setX(line);
                        counter++;
                        break;
                    }
                    case 3: {
                        lfabric.setY(line);
                        counter++;
                        break;
                    }
                    case 4: {
                        lfabric.setZ(line);
                        counter++;
                        break;
                    }
                    case 5: {
                        lfabric.setName(line);
                        counter++;
                        pfabric.setLocation(lfabric.getLocation());
                        break;
                    }
                    case 6: {
                        pfabric.setHeight(line);
                        counter++;
                        break;
                    }
                    case 7: {
                        pfabric.setPassportID(line);
                        counter++;
                        wfabric.setPerson(pfabric.getPerson());
                        break;
                    }
                    case 8: {
                        wfabric.setName(line);
                        counter++;
                        break;
                    }
                    case 9: {
                        wfabric.setSalary(line);
                        counter++;
                        break;
                    }
                    case 10: {
                        wfabric.setPosition(line);
                        counter++;
                        break;
                    }
                    case 11: {
                        wfabric.setStatus(line);
                        counter++;
                        worker = wfabric.getWorker();
                        break;
                    }
                }
            } catch (NumberFormatException | InvalidInputFormat e) {
                System.out.println(e.getMessage());
            }

        }
        worker.setCreationDate(LocalDateTime.now());
        return worker;
    }
}
