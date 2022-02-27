package Commands;

import Collection.CollectionEditor;
import Collection.WorkersCollection;
import Exceptions.InvalidInputFormat;

public class CommandExecutor {
    private WorkersCollection workers;

    public CommandExecutor(WorkersCollection workers){
        this.workers = workers;
    }

    public void executeCommand(String value) throws InvalidInputFormat {
        String[] valueParts = value.trim().split(" ");
        if (valueParts.length == 1){
            Command cmd = CmdCollection.getCommand(valueParts[0]);
            if (cmd == null){
                throw new InvalidInputFormat("Such a command does not exist");
            }
            cmd.action(workers);
        }
    }
}
