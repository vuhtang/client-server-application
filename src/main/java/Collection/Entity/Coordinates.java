package Collection.Entity;

import java.util.Objects;

public class Coordinates {
    private Long x;
    private float y;

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[x =" + getX() + ", y= " + getY() +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.y, y) == 0 && x.equals(that.x);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
