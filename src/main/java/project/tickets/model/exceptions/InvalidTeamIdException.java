package project.tickets.model.exceptions;

public class InvalidTeamIdException extends RuntimeException{
    public InvalidTeamIdException(){
        super("Wrong Id Exception");
    }
}
