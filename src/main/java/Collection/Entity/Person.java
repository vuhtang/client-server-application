package Collection.Entity;

import java.util.Objects;

public class Person {
    private Double height;
    private String passportID;
    private Location location;

    public Double getHeight() {
        return height;
    }

    public String getPassportID() {
        return passportID;
    }

    public Location getLocation() {
        return location;
    }

    public void setHeight(Double height){
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

    public int hashCode(){
        return Objects.hashCode(height) + Objects.hashCode(passportID);
    }
}
