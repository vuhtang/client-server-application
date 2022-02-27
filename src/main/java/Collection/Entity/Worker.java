package Collection.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

public class Worker {
    private int id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private long salary;
    private Position position;
    private Status status;
    private Person person;


    public int getId(){return id;}
    public String getName(){return name;}
    public Coordinates getCoordinates(){return coordinates;}

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
