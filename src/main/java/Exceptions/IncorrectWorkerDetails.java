package Exceptions;

public class IncorrectWorkerDetails extends Exception{
    private String message;
    public IncorrectWorkerDetails(String message){super(message);}
    public String getMessage(){return message;}
}
