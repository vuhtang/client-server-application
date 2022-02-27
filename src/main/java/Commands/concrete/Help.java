package Commands.concrete;

import Collection.WorkersCollection;
import Commands.CmdCollection;
import Commands.Command;

import java.util.Set;

public class Help extends Command {
    public Help(){
        super("help", "display help on available commands");
    }

    @Override
    public void action(WorkersCollection workers){
        System.out.println("\nThe list of available commands:");
        Set<Command> commands = CmdCollection.getCommands();
        for (Command command : commands) {
            System.out.println(" -" + command.getName()
                    + " //" + command.getDescription());
        }
    }
}
