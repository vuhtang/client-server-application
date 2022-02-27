package Fabrics;

import Collection.Entity.Location;

public class LocationFabric{
    private Location location = new Location();

    public LocationFabric setX(String value) throws NumberFormatException{
        try {
            Float floatValue = Float.parseFloat(value);
            location.setX(floatValue);
        } catch (NumberFormatException e){
            throw new NumberFormatException
                    ("The x coordinate of the worker's location is not correct");
        }
        return this;
    }

    public LocationFabric setY(String value) throws NumberFormatException{
        try {
            Integer integerValue = Integer.parseInt(value);
            location.setY(integerValue);
        } catch (NumberFormatException e){
            throw new NumberFormatException
                    ("The y coordinate of the worker's location in the file is not correct");
        }
        return this;
    }

    public LocationFabric setZ(String value) throws NumberFormatException{
        try {
            Long longValue = Long.parseLong(value);
            location.setZ(longValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException
                    ("The z coordinate of the worker's location in the file is not correct");
        }

        return this;
    }

    public LocationFabric setName(String value) throws NumberFormatException {
        if (value.length() > 577) throw new NumberFormatException
                ("The name of the worker's location in the file is not correct");
        location.setName(value);
        return this;
    }
    public Location getLocation(){
        return this.location;
    }
}
