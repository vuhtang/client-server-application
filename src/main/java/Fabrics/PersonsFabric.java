package Fabrics;

import Collection.Entity.Location;
import Collection.Entity.Person;

public class PersonsFabric {
    private Person person = new Person();

    public PersonsFabric setHeight(String value) throws NumberFormatException{
        try {
            Double doubleValue = Double.valueOf(value);
            if (doubleValue <= 0) throw new NumberFormatException
                    ("The height of the worker in the file is not correct");
            person.setHeight(doubleValue);
        } catch (NumberFormatException e){
            throw new NumberFormatException
                    ("The height of the worker in the file is not correct");
        }
        return this;
    }
    public PersonsFabric setPassportID(String value) throws NumberFormatException {
        if (value.length() < 4 || value.length() > 20)
            throw new NumberFormatException
                    ("The passportId of the worker in the file os not correct");
        person.setPassportID(value);
        return this;
    }
    public PersonsFabric setLocation(Location location){
        person.setLocation(location);
        return this;
    }
    public Person getPerson(){
        return person;
    }
}
