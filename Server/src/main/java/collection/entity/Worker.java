package collection.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Worker stored in the collection. Contains information about the ID, name, coordinates,
 * creation date, salary, position, status and personality of the worker.
 * Workers comparison is also implemented.
 *
 * @see Coordinates
 * @see Position
 * @see Status
 * @see Person
 */
public class Worker implements Comparable<Worker>, Serializable {
    /**
     * The ID of the worker. It's unique for each worker and is set during the creation of a new worker.
     * Must be positive.
     */
    private int id;
    /**
     * The name of the worker. Can't be empty.
     */
    private String name;
    /**
     * Coordinates of the worker. Can't be null.
     */
    private Coordinates coordinates;
    /**
     * The creation date of the worker. Can't be null.
     */
    private LocalDateTime creationDate;
    /**
     * The salary of the worker. Must be positive.
     */
    private long salary;
    /**
     * The position of the worker.
     *
     * @see Position
     */
    private Position position;
    /**
     * The status of the worker.
     *
     * @see Status
     */
    private Status status;
    /**
     * The personality of the worker. Can't be null.
     */
    private Person person;
    private String owner;

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    /**
     * Workers are compared on the basis of their salary. The higher the salary, the better the worker.
     *
     * @param o the worker to be compared
     * @return a negative integer, zero,or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Worker o) {
        return (int) (this.getSalary() - o.getSalary());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public long getSalary() {
        return salary;
    }

    public Position getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "[ ID = " + id + ", Name = " + name
                + ", PassportID = " + person.getPassportID()
//                + ", X_coordinate = " + coordinates.getX() +
//                ", Y_coordinate = " + coordinates.getY() + ", CreationDate = "
//                +creationDate.getDayOfMonth() + "." + creationDate.getMonth().getValue()
//                + "." + creationDate.getYear() + ", Salary = " + salary
//                + ", Position = " + position + ", Status = " + status
//                + ", Person's height = " + person.getHeight() + ", Person's passportID = "
//                + person.getPassportID()
//                + ", X_coordinate of person's location = " + person.getLocation().getX()
//                + ", Y_coordinate of person's location = " + person.getLocation().getY()
//                + ", Z_coordinate of person's location = " + person.getLocation().getZ()
//                + ", Name of person's location = " + person.getLocation().getName()
                + ", Owner = "  + owner + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id && salary == worker.salary && name.equals(worker.name) && coordinates.equals(worker.coordinates) && creationDate.equals(worker.creationDate) && position == worker.position && status == worker.status && person.equals(worker.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, salary, position, status, person);
    }

}
