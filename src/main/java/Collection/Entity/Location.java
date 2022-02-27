package Collection.Entity;

import java.util.Objects;

public class Location {
    private Float x;
    private Integer y;
    private Long z;
    private String name;


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
