package Exceptions;

public class InvalidInputFormat extends Exception{
    private String message;
    public InvalidInputFormat(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){return message;}
}
