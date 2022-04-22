package collection.entity;

import java.io.Serializable;

/**
 * List of all possible worker status
 */
public enum Status implements Serializable {
    FIRED,
    HIRED,
    RECOMMENDED_FOR_PROMOTION,
    REGULAR;
}
