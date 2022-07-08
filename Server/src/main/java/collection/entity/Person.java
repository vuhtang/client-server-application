package collection.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Worker personality. Contains information about the height,
 * ID of the passport and the location of the person.
 *
 * @see Location
 */
public class Person implements Serializable {
    /**
     * Person's height. Must be positive.
     */
    private Double height;
    /**
     * ID of the person's passport. It's length must be from 4 to 20.
     */
    private String passportID;
    /**
     * Person's location. Can't be null.
     */
    private Location location;
    /**
     * Initialises fields to default values.
     */
    public Person() {
        this.height = 171.0;
        this.passportID = "77777";
        this.location = new Location();
    }

    public Double getHeight() {
        return height;
    }

    public String getPassportID() {
        return passportID;
    }

    public Location getLocation() {
        return location;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return height.equals(person.height) && passportID.equals(person.passportID) && location.equals(person.location);
    }

    public int hashCode() {
        return Objects.hashCode(height) + Objects.hashCode(passportID);
    }
}
