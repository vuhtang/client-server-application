package Commands.concrete;

import Collection.WorkersCollection;
import Commands.Command;

public class Remove_At extends Command {
    public Remove_At() {
        super("remove_at", "remove an element at a give position");
    }

    @Override
    public void action(WorkersCollection workers, String args) {
        int index = -1;
        try{
            index = Integer.parseInt(args);
            if (index < 0 || index > workers.getSize()){
                System.out.println("Invalid index");
                return;
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid index");
            return;
        }
        workers.remove(index);
        System.out.println("Worker removed successfully!\n");
    }
}
