package Commands.concrete;

import Collection.CollectionEditor;
import Collection.WorkersCollection;
import Commands.Command;

import java.io.IOException;

public class Save extends Command {
    public Save(){
        super("save", "save the collection to a file");
    }

    @Override
    public void action(WorkersCollection workers, String args) {
        CollectionEditor editor = new CollectionEditor(workers);
        try {
            editor.saveCollection();
        } catch (IOException e) {
            System.out.println("Failed to save the collection to file");
        }
        System.out.println("Collection saved successfully");
    }
}
