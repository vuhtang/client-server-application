package collection.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Location of a working person.
 *
 * @see Person
 */
public class Location implements Serializable {
    /**
     * The x coordinate
     */
    private Float x;
    /**
     * The y coordinate
     */
    private Integer y;
    /**
     * The z coordinate
     */
    private Long z;

    /**
     * Name of the person's location. It's length can't be more than 577.
     */
    private String name;

    /**
     * Initialises fields to default values.
     */
    public Location() {
        this.x = 0F;
        this.y = 0;
        this.z = 0L;
        this.name = "SPB";
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x.equals(location.x) && y.equals(location.y) && z.equals(location.z) && name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }
}
