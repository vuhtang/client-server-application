package Commands;


import Commands.concrete.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CmdCollection {
    private static Set<Command> commands = new HashSet<>();
    static {
        commands.add(new Help());
        commands.add(new Exit());
        commands.add(new Save());
    }

    public static Set<Command> getCommands(){return new HashSet<>(commands);}

    public static Command getCommand(String cmdName){
        Optional<Command> cmd = commands.stream().filter(c -> c.getName().equals(cmdName)).findFirst();
        return cmd.orElse(null);
    }

}
