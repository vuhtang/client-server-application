package Fabrics;

import Collection.Entity.Coordinates;

public class CoordinatesFabric {
    private final Coordinates coordinates = new Coordinates();

    public CoordinatesFabric setX(String value) throws NumberFormatException {
        try {
            Long longValue = Long.parseLong(value);
            if (longValue > 394) throw new NumberFormatException
                    ("The x coordinate of the worker is not correct");
            coordinates.setX(longValue);
        } catch (NumberFormatException e){
            throw new NumberFormatException
                    ("The x coordinate of the worker is not correct");
        }
        return this;
    }
    public CoordinatesFabric setY(String value) throws NumberFormatException{
        try {
            float floatValue = Float.parseFloat(value);
            coordinates.setY(floatValue);
        } catch (NumberFormatException e){
            throw new NumberFormatException
                    ("The y coordinate of the worker is not correct");
        }
        return this;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
}