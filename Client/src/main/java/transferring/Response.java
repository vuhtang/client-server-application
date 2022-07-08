package transferring;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Client-server data exchange object. Send by server.
 */
public class Response implements Serializable {
    private final ArrayList<String> message;

    public Response(List<String> stringList) {
        this.message = new ArrayList<>(stringList);
    }

    public ArrayList<String> getMessage() {
        return message;
    }
}
