package factory;

import collection.entity.Coordinates;

/**
 * Factory generating objects of type Coordinates. It has setters with some validation within and
 * one getter which returns a new object.
 *
 * @see Coordinates
 */
public class CoordinatesFactory {
    /**
     * An emerging object.
     */
    private final Coordinates coordinates = new Coordinates();

    /**
     * Sets x coordinate, that can't be more than 394.
     * If it's incorrect, the new exception will be thrown.
     *
     * @param value the x coordinate less than 395
     * @return the same factory
     * @throws NumberFormatException if an input value is incorrect
     */
    public CoordinatesFactory setX(String value) throws NumberFormatException {
        try {
            long longValue = Long.parseLong(value.trim());
            if (longValue > 100 || longValue < -100) throw new NumberFormatException
                    ("The x coordinate of the worker is not correct");
            coordinates.setX(longValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException
                    ("The x coordinate of the worker is not correct");
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
    public CoordinatesFactory setY(String value) throws NumberFormatException {
        try {
            float floatValue = Float.parseFloat(value.trim());
            if (floatValue > 100 || floatValue < -100) throw new NumberFormatException
                    ("The y coordinate of the worker is not correct");
            coordinates.setY(floatValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException
                    ("The y coordinate of the worker is not correct");
        }
        return this;
    }

    /**
     * Returns a finished object
     *
     * @return a finished coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
}