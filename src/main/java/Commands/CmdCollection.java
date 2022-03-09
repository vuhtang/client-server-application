package Commands;


import Commands.concrete.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CmdCollection {
    private static final Set<Command> commands;
    static {
        Command[] cmds = {new Help(), new Exit(), new Save(), new Info(), new Add(), new Show(),
        new Update(), new Remove_By_ID(), new Clear(), new Insert_At(), new Remove_At(),
        new Remove_Lower(), new Average_Of_Salary(), new Group_Counting_By_Coordinates(),
        new Filter_By_Position(), new Execute_Script()};
        commands = new HashSet<>(Arrays.asList(cmds));
    }

    public static Set<Command> getCommands(){return new HashSet<>(commands);}

    public static Command getCommand(String cmdName){
        Optional<Command> cmd = commands.stream().filter(c -> c.getName().equals(cmdName)).findFirst();
        return cmd.orElse(null);
    }
}
