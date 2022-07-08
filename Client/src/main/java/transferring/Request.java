package transferring;

import collection.entity.Worker;

import java.io.Serializable;

/**
 * Client-server data exchange object. Send by client
 */
public class Request implements Serializable {
    private String commandName;
    private String argument;
    private Worker worker;
    private Token token;

    public Request(String commandName, String argument, Worker worker) {
        this.commandName = commandName;
        this.argument = argument;
        this.worker = worker;
    }

    public Request(String commandName, String argument) {
        this.commandName = commandName;
        this.argument = argument;
        this.worker = null;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommand(String commandName) {
        this.commandName = commandName;
    }

    public String getArgument() {
        return argument;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
