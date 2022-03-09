package Commands;

import Collection.WorkersCollection;
import Exceptions.InvalidInputFormat;

public class CommandExecutor {
    private WorkersCollection workers;

    public CommandExecutor(WorkersCollection workers){
        this.workers = workers;
    }

    public void executeCommand(String value) throws InvalidInputFormat {
        String[] valueParts = value.trim().split(" ");
        Command cmd = CmdCollection.getCommand(valueParts[0]);
        if (cmd == null) throw new InvalidInputFormat("Command \"" + valueParts[0] + "\" does not exist");
        if (valueParts.length == 1) {
            cmd.action(workers, "");
        } else if(valueParts.length == 2){
            cmd.action(workers, valueParts[1]);
        } else throw new InvalidInputFormat("Incorrect number of arguments, only one argument is expected");
    }
}
