package factory;

import collection.entity.Location;
import collection.entity.Person;

/**
 * Factory generating objects of type Person. It has setters with some validation within and
 * one getter which returns a new object.
 *
 * @see Person
 */
public class PersonsFactory {
    /**
     * An emerging object.
     */
    private final Person person = new Person();

    /**
     * Sets person's height, that must be a positive number.
     * If it's incorrect, the new exception will be thrown.
     *
     * @param value the person's height
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public PersonsFactory setHeight(String value) throws NumberFormatException {
        try {
            double doubleValue = Double.parseDouble(value.trim());
            if (doubleValue <= 0) throw new NumberFormatException
                    ("The height of the person in the file is not correct");
            person.setHeight(doubleValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException
                    ("The height of the person is not correct");
        }
        return this;
    }

    /**
     * Sets person's passport ID, length of which must be more than 3 and less than 21.
     * If it's incorrect, the new exception will be thrown.
     *
     * @param value the person's passport ID
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public PersonsFactory setPassportID(String value) throws NumberFormatException {
        if (value.trim().length() < 4 || value.trim().length() > 20)
            throw new NumberFormatException
                    ("The passportId of the person is not correct");
        person.setPassportID(value.trim());
        return this;
    }

    /**
     * Sets person's location.
     *
     * @param location the person's location
     * @return the same factory
     */
    public PersonsFactory setLocation(Location location) {
        person.setLocation(location);
        return this;
    }

    /**
     * Returns a finished object
     *
     * @return a finished person
     */
    public Person getPerson() {
        return person;
    }
}
