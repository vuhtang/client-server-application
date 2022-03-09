package Commands.concrete;

import Collection.WorkersCollection;
import Commands.Command;

public class Exit extends Command {
    public Exit(){
        super("exit", "terminate program (without saving to file)");
    }

    @Override
    public void action(WorkersCollection workers, String args){
        System.out.println("Application has completed its work");
    }
}
