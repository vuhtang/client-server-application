package Commands;

import Collection.WorkersCollection;

public abstract class Command {
    private String description; //command description
    private String name; //command name

    public Command(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    public abstract void action(WorkersCollection workers);
    public String getName(){return name;}
    public String getDescription() { return description; }
}
