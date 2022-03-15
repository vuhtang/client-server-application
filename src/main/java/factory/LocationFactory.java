package factory;

import collection.entity.Location;

/**
 * Factory generating objects of type Location. It has setters with some validation within and
 * one getter which returns a new object.
 *
 * @see Location
 */
public class LocationFactory {
    /**
     * An emerging object
     */
    private final Location location = new Location();

    /**
     * Sets x coordinate. If it's incorrect, the new exception will be thrown.
     *
     * @param value the x coordinate
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public LocationFactory setX(String value) throws NumberFormatException {
        try {
            Float floatValue = Float.parseFloat(value);
            location.setX(floatValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException
                    ("The x coordinate of the person's location is not correct");
        }
        return this;
    }

    /**
     * Sets y coordinate. If it's incorrect, the new exception will be thrown.
     *
     * @param value the y coordinate
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public LocationFactory setY(String value) throws NumberFormatException {
        try {
            Integer integerValue = Integer.parseInt(value);
            location.setY(integerValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException
                    ("The y coordinate of the person's location is not correct");
        }
        return this;
    }

    /**
     * Sets z coordinate. If it's incorrect, the new exception will be thrown.
     *
     * @param value the z coordinate
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public LocationFactory setZ(String value) throws NumberFormatException {
        try {
            Long longValue = Long.parseLong(value);
            location.setZ(longValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException
                    ("The z coordinate of the person's location is not correct");
        }

        return this;
    }

    /**
     * Sets the location name length of which can't be more than 577.
     * If it's incorrect, the new exception will be thrown.
     *
     * @param value the location name
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public LocationFactory setName(String value) throws NumberFormatException {
        if (value.length() > 577) throw new NumberFormatException
                ("The name of the person's location is not correct");
        location.setName(value);
        return this;
    }

    /**
     * Returns a finished object
     *
     * @return a finished location
     */
    public Location getLocation() {
        return this.location;
    }
}
